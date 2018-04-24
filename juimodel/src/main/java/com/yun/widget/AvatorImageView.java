package com.yun.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yun.juimodel.R;

import static me.tatarka.bindingcollectionadapter2.Utils.dp2px;

/**
 * @another 江祖赟
 * @date 2018/1/2.
 */
public class AvatorImageView extends SimpleDraweeView {
    private int mW;
    private int mAuthenW = dp2px(14);//14,16,11
    private int mAuthenH = dp2px(14);;
    private int mH;
    private Drawable mAuthenDrawable;


    public AvatorImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }


    public AvatorImageView(Context context) {
        super(context);
    }


    public AvatorImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public AvatorImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public AvatorImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        mAuthenDrawable = ContextCompat.getDrawable(getContext(), R.drawable.img_authentication_32);
    }


    public AvatorImageView setAuthenDrawableRes(@DrawableRes int authenDrawableRes) {
        mAuthenDrawable = ContextCompat.getDrawable(getContext(), authenDrawableRes);
        invalidate();
        return this;
    }


    public AvatorImageView setAuthenSize(float w, float h) {
        mAuthenW = Math.round(w);
        mAuthenH = Math.round(h);
        invalidate();
        return this;
    }


    public AvatorImageView authenSmollSize() {
        mAuthenW = dp2px(11);
        mAuthenH = dp2px(11);
        invalidate();
        return this;
    }


    public AvatorImageView authenBigSize() {
        mAuthenW = dp2px(16);
        mAuthenH = dp2px(16);
        invalidate();
        return this;
    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mW = w;
        mH = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        //暂时不管
        mAuthenDrawable.setBounds(mW - mAuthenW, mH - mAuthenH, mW, mH);
        mAuthenDrawable.draw(canvas);
    }
}
