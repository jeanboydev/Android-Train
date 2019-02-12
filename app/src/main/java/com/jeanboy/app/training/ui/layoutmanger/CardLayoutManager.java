package com.jeanboy.app.training.ui.layoutmanger;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CardLayoutManager extends RecyclerView.LayoutManager {

    private static int maxCache = 6;
    private static float sectionScale = 0.75f;
    private static float sectionTranslation = 10f;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        if (itemCount > maxCache) {
            itemCount = maxCache;
        }
        for (int i = itemCount - 1; i >= 0; i--) {//逆向
            View child = recycler.getViewForPosition(i);
            addView(child);
            measureChildWithMargins(child, 0, 0);
            //获取子 View 剩余空间
            int whiteSpace = getWidth() - getDecoratedMeasuredWidth(child);
            layoutDecoratedWithMargins(child, whiteSpace / 2, 0,
                    whiteSpace / 2 + getDecoratedMeasuredWidth(child), getDecoratedMeasuredHeight(child));

            child.setScaleX(getScaleX(i));
            child.setScaleY(getScaleY(i));
            child.setTranslationX(getTranslationX(i));
            child.setTranslationY(getTranslationY(i));
        }
    }

    private float getScaleX(int position) {
        return 1f - position * sectionScale;
    }

    private float getScaleY(int position) {
        return 1f;
    }

    private float getTranslationX(int position) {
        return 0f;
    }

    private float getTranslationY(int position) {
        return position * sectionTranslation;
    }

    @Override
    public boolean canScrollHorizontally() {
        return super.canScrollHorizontally();
    }

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
