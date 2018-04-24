package com.yun.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.blueprint.widget.FixedSpeedScroller;
import java.lang.reflect.Field;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [一句话描述]
 */
public class JViewPager extends ViewPager {
    private static final String TAG = JViewPager.class.getSimpleName();
    protected NestedScrollingChildHelper mChildHelper;
    protected boolean noScrollable;


    public JViewPager(Context context) {
        this(context, null);
    }


    public JViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mChildHelper = new NestedScrollingChildHelper(this);
        mChildHelper.setNestedScrollingEnabled(true);
    }


    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        try {
            return super.dispatchTouchEvent(event);
        } catch (IllegalArgumentException ex) {
            //            https://github.com/chrisbanes/PhotoView/issues/31
            ex.printStackTrace();
            return false;
        }
    }


    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            if (noScrollable) {
                return false;
            }
            else {
                return super.onInterceptTouchEvent(ev);
            }
        } catch (Exception e) {
            return true;
        }
    }


    @Override public boolean onTouchEvent(MotionEvent ev) {
        try {
            if (noScrollable) {
                return false;
            }
            else {
                return super.onTouchEvent(ev);
            }
        } catch (Exception e) {
            return false;
        }
    }


    public void setNoScrollable(boolean noScrollable) {
        this.noScrollable = noScrollable;
    }


    /**
     * set the duration between two slider changes.
     */
    public void setSliderTransformDuration(int period) {
        setSliderTransformDuration(period, new DecelerateInterpolator());
    }


    public void setSliderTransformDuration(int period, Interpolator interpolator) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(), interpolator, period);
            mScroller.set(this, scroller);
        } catch (Exception e) {

        }
    }


    @Override public void setNestedScrollingEnabled(boolean enabled) {
        super.setNestedScrollingEnabled(enabled);
        mChildHelper.setNestedScrollingEnabled(enabled);
    }


    @Override public boolean isNestedScrollingEnabled() {
        return mChildHelper.isNestedScrollingEnabled();
    }


    @Override public boolean startNestedScroll(int axes) {
        return mChildHelper.startNestedScroll(axes);
    }


    @Override public void stopNestedScroll() {
        mChildHelper.stopNestedScroll();
    }


    @Override public boolean hasNestedScrollingParent() {
        return mChildHelper.hasNestedScrollingParent();
    }


    @Override public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }


    @Override public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }


    @Override public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }


    @Override public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }


    /**
     * 装饰类 绘制 其他专用
     */
    public static abstract class RobeVPCanvas {
        public abstract void drawRobe(Canvas canvas, int width, int heigh);
    }
}
