package com.jeanboy.app.training.ui.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jeanboy.app.training.utils.DensityUtil;

public class FixedItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;
    private int parentWidth;
    private int itemHeight;

    public FixedItemDecoration(Context context) {
        parentWidth = context.getResources().getDisplayMetrics().widthPixels;
        itemHeight = DensityUtil.dp2px(context, 30);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setColor(Color.CYAN);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int count = parent.getChildCount();
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
//            int top = manager.getTopDecorationHeight(view);
            int top = view.getTop() - itemHeight;
            int bottom = top + itemHeight;
            Log.e(FixedItemDecoration.class.getSimpleName(), "=======itemHeight=====" + itemHeight);
            Log.e(FixedItemDecoration.class.getSimpleName(), "=======parentWidth=====" + parentWidth);
            c.drawRect(0, top, parentWidth, bottom, paint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        View child0 = parent.getChildAt(0);

        if (child0.getBottom() <= itemHeight) {
            c.drawRect(0, 0, parentWidth, child0.getBottom(), paint);
        } else {
            c.drawRect(0, 0, parentWidth, itemHeight, paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = itemHeight;
    }
}
