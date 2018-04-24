package com.yun.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @another 江祖赟
 * @date 2018/1/25.
 */
public class VideoCoordinatorLayout extends CoordinatorLayout {

    private boolean mIsVideoPlaying;

    public VideoCoordinatorLayout(Context context){
        super(context);
    }

    public VideoCoordinatorLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public VideoCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        if(mIsVideoPlaying) {
            return false;
        }else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    public boolean isVideoPlaying(){
        return mIsVideoPlaying;
    }

    public void setVideoPlaying(boolean videoPlaying){
        mIsVideoPlaying = videoPlaying;
    }
}
