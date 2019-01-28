package com.jeanboy.app.training.ui.layoutmanger;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TestLayoutManager extends RecyclerView.LayoutManager {

    /**
     * 第一步：重写这个方法一般不变
     *
     * @return
     */
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    private static int ADD_RIGHT = 1;//RecyclerView 从右往左滑动时，新出现的 child 添加在右边
    private static int ADD_LEFT = -1;//RecyclerView 从左往右滑动时，新出现的 child 添加在左边
    private OrientationHelper helper;
    //动用 scrollToPosition 后保存去到childView的位置，然后重新布局即调用onLayoutChildren
    private int mPendingScrollPosition = 0;

    /**
     * 第二步：布局子 view
     * <p>
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
        //没有 item，界面为空
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            return;
        }
        //state.isPreLayout() 是支持动画的
        if (getChildCount() == 0 && state.isPreLayout()) {
            return;
        }

        ensureStatus();//初始化OrientationHelper
        int offset = 0;
        int available = helper.getTotalSpace();
        //调用notifyDataSetChanged 才有 getChildCount() != 0
        if (getChildCount() > 0) {
            //得到第一个可见的childView
            View firstView = getChildAt(0);
            //得到第一个可见childView左边的位置
            offset = helper.getDecoratedStart(firstView);
            //获取第一个可见childView在Adapter中的position（位置）
            mPendingScrollPosition = getPosition(firstView);
            //offset的值为负数，在可见区域的左边，那么当重新布同时得考虑正偏移
            available += Math.abs(offset);
        }
        //移除所有的view
        detachAndScrapAttachedViews(recycler);
        //将可见区域的childView layout出来
        layoutScrap(recycler, state, offset, available);
    }

    private void layoutScrap(RecyclerView.Recycler recycler, RecyclerView.State state, int offset, int available) {
        for (int i = mPendingScrollPosition; i < state.getItemCount(); i++) {
            if (available <= 0) {
                break;
            }
            int childWidth = layoutScrapRight(recycler, i, offset);
            available -= childWidth;
            offset += childWidth;
        }
    }

    private int layoutScrapRight(RecyclerView.Recycler recycler, int position, int offset) {
        return layoutScrap(recycler, position, offset, ADD_RIGHT);
    }

    private int layoutScrapLeft(RecyclerView.Recycler recycler, int position, int offset) {
        return layoutScrap(recycler, position, offset, ADD_LEFT);
    }

    private int layoutScrap(RecyclerView.Recycler recycler, int position, int offset, int direction) {
        //从 recycler 中取到将要出现的childView
        View childPosition = recycler.getViewForPosition(position);
        if (direction == ADD_RIGHT) {
            addView(childPosition);
        } else {
            addView(childPosition, 0);
        }
        //计算childView的大小
        measureChildWithMargins(childPosition, 0, 0);
        int childWidth = getDecoratedMeasuredWidth(childPosition);
        int childHeight = getDecoratedMeasuredHeight(childPosition);
        if (direction == ADD_RIGHT) {
            layoutDecorated(childPosition, offset, 0, offset + childWidth, childHeight);
        } else {
            layoutDecorated(childPosition, offset - childWidth, 0, offset, childHeight);
        }
        return childWidth;
    }

    private void ensureStatus() {
        if (helper == null) {
            helper = OrientationHelper.createHorizontalHelper(this);
        }
    }

    /**
     * 第三步：设置是否可以水平滑动
     *
     * @return
     */
    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    /**
     * 第三步：设置是否可以垂直滑动
     *
     * @return
     */
    @Override
    public boolean canScrollVertically() {
        return super.canScrollVertically();
    }


    /**
     * 第四步：处理滑动-水平滑动
     *
     * @param dx
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
//        return super.scrollHorizontallyBy(dx, recycler, state);
        //回收不可见的 childView
        recyclerInvisibleView(recycler, dx);
        //将新出现的 childView layout 出来
        int willScroll = fillChild(recycler, dx, state);
        //水平方向移动childView
        offsetChildrenHorizontal(-willScroll);
        return willScroll;
    }

    private void recyclerInvisibleView(RecyclerView.Recycler recycler, int dx) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (dx > 0) {//从右向左滑动
                if (helper.getDecoratedEnd(child) - dx < getPaddingLeft()) {//将左边消失的 childView 回收掉
                    removeAndRecycleView(child, recycler);
                    break;
                }
            } else {
                if (helper.getDecoratedStart(child) - dx > getWidth() - getPaddingRight()) {
                    removeAndRecycleView(child, recycler);
                    break;
                }
            }
        }
    }

    private int fillChild(RecyclerView.Recycler recycler, int dx, RecyclerView.State state) {
        if (dx > 0) {//从右向左滑动
            View lastView = getChildAt(getChildCount() - 1);
            int position = getPosition(lastView) + 1;
            int offset = helper.getDecoratedEnd(lastView);//得到最后一个可见 childView 的右边的偏移量
            if (offset - dx < getWidth() - getPaddingRight()) {//如果右边空间足够
                if (position < state.getItemCount()) {//item 未显示完
                    layoutScrapRight(recycler, position, offset);
                } else {//已经是最后一个 item
                    return offset - getWidth() - getPaddingRight();
                }
            }
        } else {//从左向右滑动
            View firstView = getChildAt(0);
            int position = getPosition(firstView) - 1;
            int offset = helper.getDecoratedStart(firstView);
            if (offset - dx > getPaddingLeft()) {
                if (position >= 0) {
                    layoutScrapLeft(recycler, position, offset);
                } else {
                    return offset - getPaddingLeft();
                }
            }
        }
        return dx;
    }


    /**
     * 第四步：处理滑动-垂直滑动
     *
     * @param dy
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollVerticallyBy(dy, recycler, state);
    }


}
