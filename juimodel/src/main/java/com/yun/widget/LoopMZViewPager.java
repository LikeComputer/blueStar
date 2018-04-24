package com.yun.widget;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.yun.helper.BindingReusePagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [rxjava实现自动滚动轮播图，控件可见重启滾動，不可见关闭滚动]
 */
public class LoopMZViewPager extends RelativeLayout implements NestedScrollingChild {
    private static final String TAG = LoopMZViewPager.class.getSimpleName();
    private static final long LOOPINTERVAL = 2;
    private JLoopAbleViewPager mViewPager;
    private ViewPager.PageTransformer mMzTransformer = new MzTransformer();
    private NestedScrollingChildHelper mChildHelper;
    private List<? extends BindingReusePagerAdapter.JVpItem> mItems = new ArrayList<>();

    public LoopMZViewPager(Context context){
        this(context, null);
    }

    public LoopMZViewPager(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public LoopMZViewPager(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        mViewPager = new JLoopAbleViewPager(context);
        setClipChildren(false);
        setClipToPadding(false);
        //setPadding(Math.max(getPaddingLeft(),(int)DpHelper.dp2px(20)), getPaddingTop(), Math.max(getPaddingRight(),(int)DpHelper.dp2px(20)), getPaddingBottom());
        addView(mViewPager);
        mChildHelper = new NestedScrollingChildHelper(this);
        mChildHelper.setNestedScrollingEnabled(true);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        mViewPager.setPageTransformer(true, mMzTransformer);
    }

    public LoopMZViewPager setPageTransformer(ViewPager.PageTransformer transformer){
        mMzTransformer = transformer;
        mViewPager.setPageTransformer(true, mMzTransformer);
        return this;
    }

    public void setItems(List<? extends BindingReusePagerAdapter.JVpItem> items){
        mItems = items;
        mViewPager.setAdapterItems(items);
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled){
        super.setNestedScrollingEnabled(enabled);
        mChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled(){
        return mChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes){
        return mChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll(){
        mChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent(){
        return mChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow){
        return mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow){
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed){
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY){
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    static class MzTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.9F;

        @Override
        public void transformPage(View page, float position){
            //            position == [0,1] ：当前界面位于屏幕中心的时候
            //            position == [1,Infinity] ：当前Page刚好滑出屏幕右侧
            //            position ==[-Infinity,-1] ：当前Page刚好滑出屏幕左侧
            if(position<-1) {// [-Infinity,-1)
                // This page is way off-screen to the left. 当前Page刚好滑出屏幕左侧
                page.setScaleX(MIN_SCALE+0.04f);
                page.setScaleY(MIN_SCALE);
            }else if(position<=1) {
                float scale = Math.max(MIN_SCALE, 1-Math.abs(position));
                float scalex = Math.max(MIN_SCALE+0.04f, 1-Math.abs(position));
                page.setScaleY(scale);
                page.setScaleX(scalex);
            }else {// (1,+Infinity]
                // This page is way off-screen to the right.当前Page刚好滑出屏幕右侧
                page.setScaleX(MIN_SCALE+0.04f);
                page.setScaleY(MIN_SCALE);
            }
        }

    }

    public int getCurrentPosition(){
        return mViewPager.getCurrentItem();
    }

    public JLoopAbleViewPager getViewpager(){
        return mViewPager;
    }
}
