package com.jeanboy.app.training.ui.layoutmanger;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

public class FlowLayoutManager extends RecyclerView.LayoutManager {

    private int verticalOffset;//垂直方向偏移量
    private int firstVisiblePosition;//屏幕第一个可见 View 的 position
    private int lastVisiblePosition;//屏幕最后一个可见 View 的 position

    private SparseArray<Rect> itemRectArray = new SparseArray<>();

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {//没有 item
            detachAndScrapAttachedViews(recycler);//detach 所有的 item
            return;
        }
        if (getChildCount() == 0 && state.isPreLayout()) {
            return;
        }
        //onLayoutChildren() 在 RecyclerView 初始化时会执行两遍
        detachAndScrapAttachedViews(recycler);//再次 detach 所有的 item
        verticalOffset = 0;
        firstVisiblePosition = 0;
        lastVisiblePosition = getItemCount();

        fill(recycler, state);//初始化时填充子 View
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        fill(recycler, state, 0);
    }

    private int fill(RecyclerView.Recycler recycler, RecyclerView.State state, int dy) {
        //获取到 RecyclerView 的 padding 为第一个 item 做准备
        int topOffset = getPaddingTop();
        int leftOffset = getPaddingLeft();
        int lineMaxHeight = 0;//每一行的最大高度

        int startPosition = firstVisiblePosition;//开始位置
        lastVisiblePosition = getItemCount() - 1;//结束位置
        for (int i = startPosition; i <= lastVisiblePosition; i++) {
            View child = recycler.getViewForPosition(i);//取出一个 itemView
            addView(child);//添加到 RecyclerView 上
            measureChildWithMargins(child, 0, 0);//测量一下 childView 的大小

            int childHorizontalSpace = getDecoratedMeasurementHorizontal(child);
            int childVerticalSpace = getDecoratedMeasurementVertical(child);
            int parentHorizontalSpace = getHorizontalSpace();
            if (leftOffset + childHorizontalSpace <= parentHorizontalSpace) {//当前行可以放下
                //layout 子 View 并显示
                layoutDecoratedWithMargins(child, leftOffset, topOffset,
                        leftOffset + childHorizontalSpace, topOffset + childVerticalSpace);

                Rect rect = new Rect(leftOffset, topOffset + verticalOffset,
                        leftOffset + childHorizontalSpace, childVerticalSpace);
                itemRectArray.put(i, rect);

                leftOffset += childHorizontalSpace;//改变左边偏移量，为下个 item 做准备
                lineMaxHeight = Math.max(lineMaxHeight, childVerticalSpace);//获取到当前行最大的高度
            } else {//当前行空间不足，放不下
                leftOffset = getPaddingLeft();//重新获取左边偏移量
                topOffset += lineMaxHeight;//顶部加上前面的行高
                lineMaxHeight = 0;

                int parentHeight = getHeight();//RecyclerView 的高度
                int paddingBottom = getPaddingBottom();//RecyclerView 底部的 padding
                if (topOffset - dy > parentHeight - paddingBottom) {//滑动的距离超出了底部边界？
                    Log.e(FlowLayoutManager.class.getSimpleName(), "=====回收========");
                    removeAndRecycleView(child, recycler);//回收当前 item
                    lastVisiblePosition = i - 1;
                } else {
                    layoutDecoratedWithMargins(child, leftOffset, topOffset,
                            leftOffset + childHorizontalSpace, topOffset + childVerticalSpace);

                    Rect rect = new Rect(leftOffset, topOffset + verticalOffset,
                            leftOffset + childHorizontalSpace, childVerticalSpace);
                    itemRectArray.put(i, rect);

                    leftOffset += childHorizontalSpace;//改变左边偏移量，为下个 item 做准备
                    lineMaxHeight = Math.max(lineMaxHeight, childVerticalSpace);//获取到当前行最大的高度
                }
            }
        }
        return dy;
    }

    /**
     * 获取某个 view 在水平方向所占的空间
     *
     * @param view
     * @return
     */
    public int getDecoratedMeasurementHorizontal(View view) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        return getDecoratedMeasuredWidth(view) + params.leftMargin + params.rightMargin;
    }

    /**
     * 获取某个 view 在竖直方向所占的空间
     *
     * @param view
     * @return
     */
    public int getDecoratedMeasurementVertical(View view) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        return getDecoratedMeasuredHeight(view) + params.topMargin + params.bottomMargin;
    }

    /**
     * 获取 RecyclerView 水平方向上的空间
     *
     * @return
     */
    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 获取 RecyclerView 垂直方向上的空间
     *
     * @return
     */
    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }


    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (dy == 0 || getChildCount() == 0) {//没有位移，没有子 View 不需要移动
            return 0;
        }

        int realOffset = dy;//实际滑行的距离
        if (verticalOffset + realOffset < 0) {//上边界
            realOffset = -verticalOffset;
        } else if (realOffset > 0) {//下边界
            View lastChild = getChildAt(getChildCount() - 1);//获取到最后一个子 View
            if (getPosition(lastChild) == getItemCount() - 1) {
                //获取最后一个子 View 顶部位置
                int gap = getHeight() - getPaddingBottom() - getDecoratedBottom(lastChild);
                if (gap > 0) {
                    realOffset = -gap;
                } else if (gap == 0) {
                    realOffset = 0;
                } else {
                    realOffset = Math.min(realOffset, -gap);
                }
            }
        }
        realOffset = fill(recycler, state, realOffset);//先填充，再移动
        verticalOffset += realOffset;//累加滑动距离
        offsetChildrenVertical(-realOffset);//垂直方向移动所有的子 View
        return realOffset;
    }
}
