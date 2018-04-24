package com.blueprint.helper;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Size;
import android.support.v4.content.ContextCompat;

import static com.blueprint.LibApp.getContext;

public class DrawHelper {

    /**
     * 以后 绘制 文字 最好都用这个方法
     * @param canvas
     * @param paint
     * @param text
     * @param x
     * @param y
     */
    public static void getDrawText(Canvas canvas,Paint paint, String text,float x,float y) {
        canvas.drawText(text,x,getDrawTextBaseLineY(paint,y),paint);
    }

    /**
     * <b>和方法{@link #getSFontHeight(Paint)}配合使用</b>
     * 根据传入的 通过文字高度计算的画文本的最低纵坐标 计算绘制文本的baseline<br/>
     * canvas.drawText(text, start, end, x, baselineY, paint);
     */
    public static float getDrawTextBaseLineY(Paint paint, float bottom) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return bottom - (fontMetrics.descent - fontMetrics.ascent + fontMetrics.ascent);
    }

    /**
     * <b>和方法{@link #getSFontHeight2(Paint)}配合使用</b>
     * @param paint
     * @param bottom
     * @return
     */
    public static float getDrawTextBaseLineY2(Paint paint, float bottom) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return bottom - (fontMetrics.descent - fontMetrics.ascent + fontMetrics.ascent);
    }

    /**
     *
     * @param paint
     * @return
     *     文本的高度 不包括上下间距，会略小一点
     */
    public static float getFontHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return -fontMetrics.top-fontMetrics.bottom;
    }
    /**
     *
     * @param paint
     * @return
     *     计算某字符串的高度，以最高的字符为准，会略大一点
     */
    public static float getFontHeight2(Paint paint,String text) {
        Rect bounds = new Rect();
        paint.getTextBounds(text,0,text.length(),bounds);
        return bounds.height();
    }

    public static void drawTextCenterinRectf(Canvas canvas, RectF rectF, Paint textPaint, String msg) {
        canvas.drawText(msg, rectF.centerX(), rectF.centerY() + getFontHeight(textPaint) / 2f, textPaint);
    }


    //https://blog.csdn.net/u012551350/article/details/51361778

    /**
     * @return 系统建议的文字高度 descent-到-ascent的高度
     * <p>
     *     <b>和方法{@link #getDrawTextBaseLineY(Paint, float)}配合使用</b>
     * top：可绘制的最高高度所在线     fontMetrics.top 负数，top到baseline的距离
     * ascent ：系统建议的，绘制单个字符时，字符应当的最高高度所在线  fontMetrics.ascent 负数，ascent到baseline的距离
     * bottom：可绘制的最低高度所在线   fontMetrics.bottom 正数，bottom到baseline的距离
     * descent：系统建议的，绘制单个字符时，字符应当的最低高度所在线  fontMetrics.descent 正数，descent到baseline的距离
     */
    public static float getSFontHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.descent - fontMetrics.ascent;
    }


    public static int getSFontHeightInt(Paint paint) {
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        return fontMetricsInt.descent - fontMetricsInt.ascent;
    }


    /**
     * @return 可绘制的文字的最大 高度 bottom-到-top的高度
     * <p>
     *     <b>和方法{@link #getDrawTextBaseLineY2(Paint, float)}配合使用</b>
     *
     * top：可绘制的最高高度所在线     fontMetrics.top 负数，top到baseline的距离
     * ascent ：系统建议的，绘制单个字符时，字符应当的最高高度所在线  fontMetrics.ascent 负数，ascent到baseline的距离
     * bottom：可绘制的最低高度所在线   fontMetrics.bottom 正数，bottom到baseline的距离
     * descent：系统建议的，绘制单个字符时，字符应当的最低高度所在线  fontMetrics.descent 正数，descent到baseline的距离
     */
    public static float getSFontHeight2(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.bottom - fontMetrics.top;
    }


    public static int getSFontHeightInt2(Paint paint) {
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        return fontMetricsInt.bottom - fontMetricsInt.top;
    }

    /**
     * 计算反色
     *
     * @param color
     * @return
     */
    public static int getConverserColor(int color) {
        return Color.rgb(255 - Color.red(color), 255 - Color.green(color), 255 - Color.blue(color));
    }

    /**
     * [checked,pressed],normal
     *
     * @param resIds
     * @return
     */
    public static StateListDrawable getListDrable(@Size(value = 2) @DrawableRes int... resIds) {
        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[]{android.R.attr.state_checked}, ContextCompat.getDrawable(getContext(), resIds[0]));
        listDrawable.addState(new int[]{android.R.attr.state_pressed}, ContextCompat.getDrawable(getContext(), resIds[0]));
        listDrawable.addState(new int[]{}, ContextCompat.getDrawable(getContext(), resIds[1]));
        return listDrawable;
    }

    public static ColorStateList getColorStateList(@Size(value = 2) @ColorInt int... colors) {
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_checked};
        states[1] = new int[]{};
        return new ColorStateList(states, colors);
    }

    /**
     * checked,pressed,normal
     *
     * @param colors
     * @return
     */
    public static ColorStateList getColorStateList2(@Size(value = 3) @ColorInt int... colors) {

        int[][] states = new int[][]{new int[]{android.R.attr.state_checked},// unchecked
                new int[]{android.R.attr.state_pressed},//pressed
                new int[]{}   //normal
        };
        return new ColorStateList(states, colors);
    }

    //==============================  加速器  ========================================

    /**
     * <a href="https://github.com/daimajia/AnimationEasingFunctions>AnimationEasingFunctions</a>
     **/

    public static final class 加速运动 {
        //    加速运动
        //    AccelerateInterpolator mAccelerateInterpolator;
        private static final float mFactor = 1;
        private static final double mDoubleFactor = 2;

        public static float getInterpolation(float input) {
            if (mFactor == 1.0f) {
                return input * input;
            } else {
                return (float) Math.pow(input, mDoubleFactor);
            }
        }
    }

    public static final class 先后退一小步然后向前加速 {
        //    先后退一小步然后向前加速
        //    AnticipateInterpolator mAnticipateInterpolator;
        private static final float mTension = 2;

        public static float getInterpolation(float t) {
            // a(t) = t * t * ((tension + 1) * t - tension)
            return t * t * ((mTension + 1) * t - mTension);
        }
    }

    public static final class 减速运动 {
        private static final float mTension = 2;

        public static float getInterpolation(float t) {
            // _o(t) = t * t * ((tension + 1) * t + tension)
            // o(t) = _o(t - 1) + 1
            t -= 1.0f;
            return t * t * ((mTension + 1) * t + mTension) + 1.0f;
        }
    }

    public static final class 弹球效果弹几下回到终点 {
        //    弹球效果弹几下回到终点
        //    BounceInterpolator mBounceInterpolator;

        public static float getInterpolation(float t) {
            // _b(t) = t * t * 8
            // bs(t) = _b(t) for t < 0.3535
            // bs(t) = _b(t - 0.54719) + 0.7 for t < 0.7408
            // bs(t) = _b(t - 0.8526) + 0.9 for t < 0.9644
            // bs(t) = _b(t - 1.0435) + 0.95 for t <= 1.0
            // b(t) = bs(t * 1.1226)
            t *= 1.1226f;
            if (t < 0.3535f) {
                return bounce(t);
            } else if (t < 0.7408f) {
                return bounce(t - 0.54719f) + 0.7f;
            } else if (t < 0.9644f) {
                return bounce(t - 0.8526f) + 0.9f;
            } else {
                return bounce(t - 1.0435f) + 0.95f;
            }
        }

        private static float bounce(float t) {
            return t * t * 8.0f;
        }
    }
}
