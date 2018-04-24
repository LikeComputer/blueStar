package com.yun.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import com.yun.helper.BindingReusePagerAdapter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [一句话描述]
 */
public class JLoopAbleViewPager extends JViewPager {
    private static final String TAG = JLoopAbleViewPager.class.getSimpleName();
    private static final long LOOPINTERVAL = 2;
    private Disposable mSubscribe;
    private PointF mLastMoved = new PointF();
    private int mCurrentPosition = 0;
    private boolean mAutoLoop = false;
    private JViewPager.RobeVPCanvas mRobeVPCanvas;
    private int mW;
    private int mH;
    private List<? extends BindingReusePagerAdapter.JVpItem> mItems = new ArrayList<>(1);


    public JLoopAbleViewPager(Context context) {
        this(context, null);
    }


    public JLoopAbleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOffscreenPageLimit(3);
        setSliderTransformDuration(300, new DecelerateInterpolator());
        setAutoLoop(true);
        setAdapter(new BindingReusePagerAdapter<BindingReusePagerAdapter.JVpItem>() {
            @Override public int getCount() {
                return mItems.size() > 1 ? Integer.MAX_VALUE : 1;
            }


            @Override protected int getRealPosition(int position) {
                return position % mItems.size();
            }
        });
        addOnPageChangeListener(new SimpleOnPageChangeListener(){
            @Override public void onPageSelected(int position) {
                super.onPageSelected(position);
                mCurrentPosition = position;
            }
        });
    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mW = w;
        mH = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }


    private synchronized void startLoop() {
        if (!mAutoLoop || getAdapter() == null || getAdapter().getCount() <= 1) {
            return;
        }
        if (mSubscribe == null || mSubscribe.isDisposed()) {
            setCurrentItem(mCurrentPosition);
            mSubscribe = Observable.interval(LOOPINTERVAL, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                @Override public void accept(Long aLong) throws Exception {
                    setCurrentItem(++mCurrentPosition);
                }
            });
        }
    }


    private void stopLoop() {
        if (mAutoLoop && mSubscribe != null) {
            mCurrentPosition = getCurrentItem();
            mSubscribe.dispose();
        }
    }


    public void setAdapterItems(@NonNull List<? extends BindingReusePagerAdapter.JVpItem> items) {
        if (getAdapter() == null || !(getAdapter() instanceof BindingReusePagerAdapter)) {
            throw new RuntimeException("必须先设置adapter 类型必须是BindingReusePagerAdapter");
        }
        if (mItems.size() != items.size()) {
            mItems = items;
            ((BindingReusePagerAdapter) getAdapter()).setItems(items);
            if (getAdapter().getCount() > 1) {
                mCurrentPosition = 100 / mItems.size();
                setCurrentItem(mCurrentPosition);
                startLoop();
            }
        }
    }


    public boolean isAutoLoop() {
        return mAutoLoop;
    }


    public void setAutoLoop(boolean autoLoop) {
        mAutoLoop = autoLoop;
    }


    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        try {
            if (mAutoLoop && getAdapter() != null && getAdapter().getCount() > 1) {
                //在列表中 上下滚动事件传不到 但是能够接收少量move事件
                //所以简单的down就停止不够，当上下滑动开启循环
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mLastMoved.set(event.getX(), event.getY());
                        stopLoop();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(event.getX() - mLastMoved.x) < Math.abs(event.getY() - mLastMoved.y)) {
                            mChildHelper.stopNestedScroll();
                            //down的时候关了
                            startLoop();
                        }
                        else {
                            mChildHelper.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
                            stopLoop();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mChildHelper.stopNestedScroll();
                        startLoop();
                        break;
                }
            }
            return super.dispatchTouchEvent(event);
        } catch (IllegalArgumentException ex) {
            //            https://github.com/chrisbanes/PhotoView/issues/31
            ex.printStackTrace();
            return false;
        }
    }

    public void setNoScrollable(boolean noScrollable) {
        this.noScrollable = noScrollable;
    }


    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //往下滚动到看不到
        stopLoop();
    }


    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //向上滚动到可见
        if (getAdapter().getCount() > 0 && mCurrentPosition != 0) {
            startLoop();
        }
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRobeVPCanvas != null) {
            mRobeVPCanvas.drawRobe(canvas, mW, mH);
        }
    }


    public JViewPager.RobeVPCanvas getRobeVPCanvas() {
        return mRobeVPCanvas;
    }


    public JLoopAbleViewPager setRobeVPCanvas(JViewPager.RobeVPCanvas robeVPCanvas) {
        mRobeVPCanvas = robeVPCanvas;
        return this;
    }

}
