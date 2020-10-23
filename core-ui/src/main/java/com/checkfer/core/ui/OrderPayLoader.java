package com.checkfer.core.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;


/**
 * LOADER for ORDERPAY
 * Create by : Manish
 */
public class OrderPayLoader extends AppCompatImageView {

    private AnimationDrawable loaderAnimation;

    public OrderPayLoader(Context context) {
        super(context);
        inItView(context);
    }

    public OrderPayLoader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inItView(context);
    }

    public OrderPayLoader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inItView(context);
    }

    private void inItView(Context context) {
        setBackground(ContextCompat.getDrawable(context, R.drawable.loader_animation_list));
        loaderAnimation = (AnimationDrawable) getBackground();
        startLoader();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (loaderAnimation != null) {
            loaderAnimation = null;
        }
    }

    public void startLoader() {
        if (loaderAnimation != null) {
            loaderAnimation.start();
        }
    }

    public void stopLoader() {
        if (loaderAnimation != null) {
            loaderAnimation.stop();
        }
    }

}
