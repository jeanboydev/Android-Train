package com.jeanboy.app.training.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.jeanboy.app.training.R;
import com.jeanboy.app.training.base.BaseActivity;

public class AnimButtonActivity extends BaseActivity {

    private View btn_action;
    private ImageView iv_icon_before, iv_icon_after;

    private boolean isTransformed = false;

    @Override
    protected String getTAG() {
        return AnimButtonActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_button);

        btn_action = findViewById(R.id.btn_action);
        iv_icon_before = findViewById(R.id.iv_icon_before);
        iv_icon_after = findViewById(R.id.iv_icon_after);


        btn_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTransformed) {
                    revertAnim();
                } else {
                    startAnim();
                }
                isTransformed = !isTransformed;
            }
        });
    }

    private void startAnim() {
        final int fromColor = getResources().getColor(R.color.bg_action_red);
        final int toColor = getResources().getColor(R.color.bg_action_blue);
        transformColor(btn_action, fromColor, toColor, 500, false);
        transformIcon(iv_icon_before, iv_icon_after, 500, false);
    }

    private void revertAnim() {
        final int fromColor = getResources().getColor(R.color.bg_action_red);
        final int toColor = getResources().getColor(R.color.bg_action_blue);
        transformColor(btn_action, fromColor, toColor, 500, true);
        transformIcon(iv_icon_before, iv_icon_after, 500, true);
    }

    private void transformIcon(View from, View to, int duration, boolean isRevert) {
        ObjectAnimator rotationFrom = ObjectAnimator.ofFloat(isRevert ? to : from, "rotation", 0, isRevert ? -90 : 90);
        ObjectAnimator alphaFrom = ObjectAnimator.ofFloat(isRevert ? to : from, "alpha", 1, 0);
        ObjectAnimator rotationTo = ObjectAnimator.ofFloat(isRevert ? from : to, "rotation", isRevert ? 90 : -90, 0);
        ObjectAnimator alphaTo = ObjectAnimator.ofFloat(isRevert ? from : to, "alpha", 0, 1);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotationFrom).with(alphaFrom).with(rotationTo).with(alphaTo);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.setDuration(duration);
        animatorSet.start();
    }

    private void transformColor(final View view, int from, int to, int duration, boolean isRevert) {
        ValueAnimator valueAnimator = ValueAnimator.ofArgb(isRevert ? to : from, isRevert ? from : to);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int colorValue = (int) animation.getAnimatedValue();
                RippleDrawable rippleDrawable = (RippleDrawable) view.getBackground();
                GradientDrawable gradientDrawable = (GradientDrawable) rippleDrawable.getDrawable(0);
                gradientDrawable.setColor(colorValue);
            }
        });
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }
}
