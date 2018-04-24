package com.blueprint.helper.spannable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.style.ReplacementSpan;

/**
 * @another 江祖赟
 * @date 2018/4/21.
 */
public class FrameSpan extends ReplacementSpan {
    private final Paint mPaint;
    private int mWidth;
    private final int mColor;


    public FrameSpan() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mColor = Color.parseColor("#08D080");
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
    }


    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        mPaint.setTextSize(paint.getTextSize()*10/12);
        mWidth = (int) mPaint.measureText(text, start, end);
        return mWidth;
    }


    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        canvas.drawRect(x, top, x + mWidth, bottom, mPaint);
        paint.setColor(mColor);
        paint.setTextSize(paint.getTextSize()*10/12);
        canvas.drawText(text, start, end, x, y, paint);
    }
}
