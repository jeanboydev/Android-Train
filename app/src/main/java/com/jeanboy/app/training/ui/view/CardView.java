package com.jeanboy.app.training.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class CardView extends View {
    public CardView(Context context) {
        super(context);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    private Droid[] mDroids;
//
//    /**
//     * The width of the droid image. In this sample, we hard code an image width in
//     * DroidCardsActivity and pass it as an argument to this view.
//     */
//    float mDroidImageWidth;
//
//    /**
//     * The distance between the left edges of two adjacent cards. The cards overlap horizontally.
//     */
//    protected float mCardSpacing;
//
//    /**
//     * Keeps track of the left coordinate for each card.
//     */
//    private float mCardLeft;
//
//    /**
//     * A list of DroidCards objected. We use Asynctasks to populate the contents of this list. See
//     * the DroidCardWorkerTask class that extends AsyncTask.
//     */
//    private ArrayList<DroidCard> mDroidCards = new ArrayList<DroidCard>();
//
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        if (mDroids.length > 0 && mDroidCards.size() == mDroids.length) {
//            int i;
//            for (i = 0; i < mDroidCards.size() - 1; i++) {
//                // 每张卡片都放在前一张卡片的右侧
//                mCardLeft = i * mCardSpacing;
//                // 保存 canvas 的状态
//                canvas.save();
//                // 将绘图区域限制为可见的区域
//                canvas.clipRect(mCardLeft, 0, mCardLeft + mCardSpacing, mDroidCards.get(i).getHeight());
//
//                drawDroidCard(canvas, mDroidCards.get(i), mCardLeft, 0);
//                // 将画布恢复到非剪切状态
//                canvas.restore();
//            }
//
//            // 绘制最后没有剪裁的卡片
//            drawDroidCard(canvas, mDroidCards.get(i), mCardLeft + mCardSpacing, 0);
//        }
//
//        invalidate();
//    }
//
//    protected void drawDroidCard(Canvas canvas, DroidCard droidCard, float left, float top) {
//
//    }
}
