package com.blueprint.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @another 江祖赟
 * @date 2017/8/25
 * { https://code.google.com/p/android/issues/detail?id=60464}
 */
public class ViewPagerFixed extends android.support.v4.view.ViewPager {

    public static final int TAG_VP_HOLDER = 0x11111111;
    private boolean mIsDisallowIntercept = false;

    public ViewPagerFixed(Context context){
        super(context);
    }

    public ViewPagerFixed(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept){
        // keep the info about if the innerViews do
        // requestDisallowInterceptTouchEvent
        mIsDisallowIntercept = disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        // the incorrect array size will only happen in the multi-touch
        // scenario.
        if(ev.getPointerCount()>1 && mIsDisallowIntercept) {
            requestDisallowInterceptTouchEvent(false);
            boolean handled = super.dispatchTouchEvent(ev);
            requestDisallowInterceptTouchEvent(true);
            return handled;
        }else {
            return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev){
        try {
            return super.onTouchEvent(ev);
        }catch(IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        try {
            return super.onInterceptTouchEvent(ev);
        }catch(IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
