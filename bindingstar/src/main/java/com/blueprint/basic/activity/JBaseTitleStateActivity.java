package com.blueprint.basic.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import com.blueprint.R;
import com.blueprint.databinding.JbasicStateSwipeLayoutBinding;
import jonas.jlayout.MultiStateLayout;
import jzy.easybindpagelist.statehelper.BaseDiffSteteViewModel;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [标题+状态界面  有统一处理 basePresenter的subscribe和unsubscribe]
 */
public abstract class JBaseTitleStateActivity<VM extends BaseDiffSteteViewModel, DBI extends ViewDataBinding>
        extends JBaseTitleActivity<Object, VM, DBI> {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MultiStateLayout mStateContainerLayout;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = initViewModel();
        JbasicStateSwipeLayoutBinding jbasicStateSwipeLayoutBinding = DataBindingUtil.setContentView(this, setContentLayout4Act());
        mContainerLayout = jbasicStateSwipeLayoutBinding.rootContainer;
        mToolBar = jbasicStateSwipeLayoutBinding.jbaseToolbar;
        mSwipeRefreshLayout = jbasicStateSwipeLayoutBinding.jbaseSwipe;
        mStateContainerLayout = jbasicStateSwipeLayoutBinding.jbaseStateContainer;
        mViewDataBinding = onCreateContent(getLayoutInflater(), mContainerLayout);
        mContentView = mViewDataBinding.getRoot();
        if(mContentView.getParent() != mContainerLayout) {
            mContainerLayout.addView(mContentView);
        }
        if(requestNoTitleBar()) {
            mToolBar.setVisibility(View.GONE);
        } else {
            initToolBar();
            reConfigTitleBar(mToolBar);
            if(setContentBelowTitleBar()) {
                stateLayoutBelowTitleBar();
            }
        }
        jbasicStateSwipeLayoutBinding.setJbaseStateViewModel(mViewModel);
        mViewDataBinding.setVariable(setVariableId(), mViewModel);
        setMoreVariableView(mViewDataBinding);
        toSubscribeData();
    }

    @Override protected int setContentLayout4Act() {
        return R.layout.jbasic_state_swipe_layout;
    }

    protected abstract int setVariableId();

    protected void setMoreVariableView(DBI viewDataBinding){}
}
