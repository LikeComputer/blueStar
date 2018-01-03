package com.n4399.miniworld.vp.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.blueprint.basic.JBasePresenter;
import com.blueprint.widget.JTitleBar;
import com.jakewharton.rxbinding2.view.RxView;
import com.n4399.miniworld.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import jonas.jlayout.MultiStateLayout;
import jonas.jlayout.OnStateClickListener;

import static com.blueprint.LibApp.setTextView;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [标题+状态界面  有统一处理 basePresenter的subscribe和unsubscribe]
 */
public abstract class JBaseTitleActivity extends JBaseActivity implements OnStateClickListener {
    public MultiStateLayout mMultiStateLayout;
    public JTitleBar mTitleBar;
    public JBasePresenter mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mBasePresenter = initPresenter();
        setContentView(R.layout.jbasic_title_state_layout);
        mMultiStateLayout = (MultiStateLayout)findViewById(R.id.jbase_state_container);
        mMultiStateLayout.setOnStateClickListener(this);
        mTitleBar = (JTitleBar)findViewById(R.id.jbase_titlebar);
        onCreateContent(getLayoutInflater(), mMultiStateLayout);
        if(requestNoTitleBar()) {
            mTitleBar.setVisibility(View.GONE);
        }else {
            initStableViews();
        }
    }

    protected abstract JBasePresenter initPresenter();

    @Override
    public void onAttachedToWindow(){
        super.onAttachedToWindow();
        toSubscribe();
    }

    protected void toSubscribe(){
        if(mBasePresenter != null) {
            mBasePresenter.subscribe(null);
        }
    }

    /**
     * 默认 有titlebar
     *
     * @return
     */
    protected boolean requestNoTitleBar(){
        return false;
    }

    private void initStableViews(){
        mTitleBar.getTitleTextView().setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        //标题内容
        setTextView(mTitleBar.getTitleTextView(), setTitle());
        //空页面提示信息
        setTextView(mMultiStateLayout.getEmptyLayout(), R.id.j_multity_empt_msg, setEmptMsg());
        setTextView(mMultiStateLayout.getEmptyLayout(), R.id.j_multity_retry, setEmptRetryMsg());
        //错误页面提示信息
        setTextView(mMultiStateLayout.getErrorLayout(), R.id.j_multity_error_msg, setErrorMsg());
        setTextView(mMultiStateLayout.getErrorLayout(), R.id.j_multity_retry, setErrorRetryMsg());

        setClicks();

        refreshTitleBar(mTitleBar);
    }

    /**
     * 复写 更新titlebar样式
     *
     * @param titleBar
     */
    protected void refreshTitleBar(JTitleBar titleBar){

    }

    private void setClicks(){
        //左边的点击事件
        RxView.clicks(mTitleBar.getLiftIcon()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object aVoid) throws Exception{
                doTitleBarLeftClick();
            }
        });
        RxView.clicks(mTitleBar.getRightIcon()).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object aVoid) throws Exception{
                doTitleBarRightClick();
            }
        });
    }

    protected void doTitleBarRightClick(){

    }

    protected void doTitleBarLeftClick(){
        onBackPressed();
    }

    protected CharSequence setErrorRetryMsg(){
        return null;
    }

    protected CharSequence setEmptRetryMsg(){
        return null;
    }

    protected String setErrorMsg(){
        return "";
    }

    protected String setEmptMsg(){
        return "";
    }

    protected String setTitle(){
        return null;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mBasePresenter != null) {
            mBasePresenter.unsubscribe();
        }
    }

    /**
     * 将布局添加到 container中
     *
     * @param inflater
     * @param container
     */
    protected abstract void onCreateContent(LayoutInflater inflater, RelativeLayout container);

    @Override
    public void onRetry(int layoutState){
        toSubscribe();
    }

    @Override
    public void onLoadingCancel(){

    }
}
