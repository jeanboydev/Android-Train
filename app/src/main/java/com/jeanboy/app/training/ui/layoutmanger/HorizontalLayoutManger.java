package com.jeanboy.app.training.ui.layoutmanger;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

public class HorizontalLayoutManger extends RecyclerView.LayoutManager {

    private static final String TAG = HorizontalLayoutManger.class.getSimpleName();

    private SparseArray<Rect> itemRectArray = new SparseArray<>();//记录 item 位置

    private int scrollOffset = 0;//滑动的距离
//    private OrientationHelper helper;

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

        int itemCount = getItemCount();
        Log.e(TAG, "=======onLayoutChildren=====itemCount====" + itemCount);
        int startPosition = 0;
        int endPosition = itemCount - 1;
        int leftOffset = 0;
        for (int i = startPosition; i <= endPosition; i++) {
            View child = recycler.getViewForPosition(i);//获取到子 View
            addView(child);//将子 View 添加到 RecyclerView
            measureChildWithMargins(child, 0, 0);//测量一下子 View 的大小

            int childWidth = getDecoratedMeasurementHorizontal(child);//得到子 View 的宽度
            int childHeight = getDecoratedMeasurementVertical(child);//得到子 View 的高度
            int topOffset = getPaddingTop();//得到父 View 的顶部间距
            layoutDecoratedWithMargins(child, leftOffset, topOffset, leftOffset + childWidth,
                    topOffset + childHeight);

            Rect rect = new Rect(leftOffset, topOffset, leftOffset + childWidth,
                    topOffset + childHeight);
            itemRectArray.put(i, rect);

            leftOffset += childWidth;
        }
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

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.e(TAG, "=======scrollHorizontallyBy=====dx====" + dx);
        Log.e(TAG, "=======scrollHorizontallyBy=====scrollOffset====" + scrollOffset);

        int realOffset = dx;

        if (dx < 0) {//从左向右滑动 ->
            if (scrollOffset + realOffset < 0) {//左边边界
                realOffset = -scrollOffset;
            }
        } else {//从右向左滑动 <-
            View lastChild = getChildAt(getChildCount() - 1);//获取到当前最后一个子 View
            Log.e(TAG, "=======scrollHorizontallyBy=====getPosition(lastChild)====" + getPosition(lastChild));
            Log.e(TAG, "=======scrollHorizontallyBy=====getItemCount()====" + getItemCount());
            if (getPosition(lastChild) == getItemCount() - 1) {//判断当前最后一个子 View 是否是列表最后一个 View
                int parentWidth = getWidth();
                int parentPaddingRight = getPaddingRight();
                int childRight = getDecoratedRight(lastChild);
                int lastOffset = parentWidth - parentPaddingRight - childRight;
                Log.e(TAG, "=======scrollHorizontallyBy=====realOffset====" + realOffset);
                Log.e(TAG, "=======scrollHorizontallyBy=====lastOffset====" + lastOffset);
                if (lastOffset > 0) {//滑动超过了右边边界
                    realOffset = -lastOffset;
                } else if (lastOffset == 0) {
                    realOffset = 0;
                } else {
                    realOffset = Math.min(realOffset, -lastOffset);
                }
            }
        }
        handleRecycle(recycler, state);
        scrollOffset += realOffset;
        offsetChildrenHorizontal(-realOffset);//移动子 View
        return realOffset;
    }

    private void handleRecycle(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        Rect displayRect = getDisplayRect();
        for (int i = 0; i < getItemCount(); i++) {
            View child = recycler.getViewForPosition(i);//取出一个 item
            Rect rect = itemRectArray.get(i);
            if (Rect.intersects(displayRect, rect)) {//判断 item 是否在显示范围内
//                Log.e(TAG, "=====handleRecycle==不回收===position=" + i);
                addView(child);//将子 View 添加到 RecyclerView
                measureChildWithMargins(child, 0, 0);//测量一下子 View 的大小
                layoutDecoratedWithMargins(child, rect.left - scrollOffset, rect.top,
                        rect.right - scrollOffset, rect.bottom);
            } else {
                Log.e(TAG, "=====handleRecycle==回收===position=" + i);
                removeAndRecycleView(child, recycler);
            }
        }
    }

    private Rect getDisplayRect() {
        int horizontalWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int verticalHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        return new Rect(getPaddingLeft(), getPaddingTop(),
                horizontalWidth + scrollOffset, verticalHeight);
    }
}
