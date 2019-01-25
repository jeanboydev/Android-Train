package com.jeanboy.app.training.ui.layoutmanger;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TestLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }


    private int verticalOffset;//垂直偏移量
    private int firstPosition;//屏幕可见的第一个 view 的 position
    private int latestPositon;//屏幕可见的最后一个 view 的 position

    /**
     * RecyclerView 初始化时会调用两次
     * adapter.notifyDataSetChanged() 时，会被调用
     * setAdapter 替换 Adapter 时,会被调用
     * RecyclerView 执行动画时，它也会被调用
     *
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
        if (getItemCount() == 0) {//没有 item，界面为空
            detachAndScrapAttachedViews(recycler);
            return;
        }
        if (getChildCount() == 0 && state.isPreLayout()) {//state.isPreLayout() 是支持动画的
            return;
        }
        //onLayoutChildren 方法在 RecyclerView 初始化时会执行两次
        detachAndScrapAttachedViews(recycler);
        verticalOffset = 0;
        firstPosition = 0;
        latestPositon = getItemCount();
        //初始化时调用 填充 childView
        fill(recycler, state);
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int topOffset = getPaddingTop();//布局时向上偏移
        int leftOffset = getPaddingLeft();//布局时向左偏移
        int itemMaxHeight = 0;//每一行最大高度
        int minPositon = firstPosition;
        latestPositon = getItemCount() - 1;

        for (int i = minPositon; i <= latestPositon; i++) {
            View child = recycler.getViewForPosition(i);
            addView(child);
            measureChildWithMargins(child, 0, 0);
            if (leftOffset + getDecoratedMeasurementHorizontal(child) <= getHorizontalSpace()) {//当前行还有空间
                layoutDecoratedWithMargins(child, leftOffset, topOffset,
                        leftOffset + getDecoratedMeasurementHorizontal(child), topOffset + getDecoratedMeasurementVertical(child));
                leftOffset += getDecoratedMeasurementHorizontal(child);
                itemMaxHeight = Math.max(itemMaxHeight, getDecoratedMeasurementVertical(child));
            } else {//换行
                leftOffset += getPaddingLeft();
                topOffset += itemMaxHeight;
                itemMaxHeight = 0;

                if (topOffset - dy > getHeight() - getPaddingBottom()) {//是否超出屏幕
                    removeAndRecycleView(child, recycler);
                    latestPositon = i - 1;
                } else {
                    layoutDecoratedWithMargins(child, leftOffset, topOffset,
                            leftOffset + getDecoratedMeasurementHorizontal(child), topOffset + getDecoratedMeasurementVertical(child));
                    leftOffset += getDecoratedMeasurementHorizontal(child);
                    itemMaxHeight = Math.max(itemMaxHeight, getDecoratedMeasurementVertical(child));
                }
            }
        }
    }

    /**
     * 获取水平宽度
     *
     * @param view
     * @return
     */
    private int getDecoratedMeasurementHorizontal(View view) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        return getDecoratedMeasuredWidth(view) + params.leftMargin + params.rightMargin;
    }

    /**
     * 获取垂直高度
     *
     * @param view
     * @return
     */
    public int getDecoratedMeasurementVertical(View view) {
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        return getDecoratedMeasuredHeight(view) + params.topMargin + params.bottomMargin;
    }

    public int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    public int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 是否可以水平滑动
     *
     * @return
     */
    @Override
    public boolean canScrollHorizontally() {
        return super.canScrollHorizontally();
    }

    /**
     * 是否可以垂直滑动
     *
     * @return
     */
    @Override
    public boolean canScrollVertically() {
        return super.canScrollVertically();
    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollVerticallyBy(dy, recycler, state);
    }


}
