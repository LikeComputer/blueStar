package com.blueprint.basic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blueprint.R;

import jzy.easybindpagelist.statehelper.StateDiffViewModel;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [标题+状态界面  有统一处理 basePresenter的subscribe和unsubscribe]
 * 在 onAttachtoWindow会获取数据
 * onContentChanged在setContentView内部调用 然后基类才获取各种控件，所以在onContentChanged中可以获取intent中的数据，就不需要在super.onCreate之前获取数据
 */
public abstract class JBaseTitleActivity<D, VM extends StateDiffViewModel<D>>
        extends JBaseVMActivity<VM> {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override protected int resetActLayoutRes() {
        return R.layout.jbasic_title_layout;
    }


    @Override protected void findStatbleViews(View rootView) {
        mToolBar = rootView.findViewById(R.id.jbase_toolbar);
        mContainerLayout = rootView.findViewById(R.id.root_container);
    }


    @Override protected void layoutViews() {
        mLayoutBelowToolbar = mContentView;
        super.layoutViews();
    }


    @Override public void setContentView(int layoutResID) {
        if(mContainerLayout == null) {
            super.setContentView(layoutResID);
        } else {
            //DataBindingUtil.setContentView(activity, layout);
            mContentView = getLayoutInflater().inflate(layoutResID, mContainerLayout);
        }
    }

    //onResume之后
    @Override public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }


    @Override public void onContentChanged() {
        super.onContentChanged();
        //该方法是在 setContentView()内部触发的 并不是onCreate结束之后才执行
    }


    /**
     * 类似 firstVisibility() 第一次获取数据入口
     */
    protected void toSubscribeData(VM viewModel) {
    }

}
