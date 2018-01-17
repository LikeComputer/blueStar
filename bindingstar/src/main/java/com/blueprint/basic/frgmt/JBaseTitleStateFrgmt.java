package com.blueprint.basic.frgmt;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.blueprint.R;
import jzy.easybindpagelist.statehelper.BaseDiffSteteViewModel;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [标题+状态界面  有统一处理 basePresenter的subscribe和unsubscribe]
 * onCreate中获取 getArguments数据
 */
public abstract class JBaseTitleStateFrgmt<VM extends BaseDiffSteteViewModel, DBI extends ViewDataBinding>
        extends JBaseVMFrgmt<VM, DBI> {
    protected SwipeRefreshLayout mSwipeRefreshLayout;


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override protected int resetFrgmtLayoutRes() {
        return R.layout.jbasic_state_swipe_layout;
    }


    @Override protected void findStatbleViews(View rootView) {
        mContainerLayout = rootView.findViewById(R.id.jbase_container);
        mToolBar = rootView.findViewById(R.id.jbase_toolbar);
        mLayoutBelowToolbar = mSwipeRefreshLayout = rootView.findViewById(R.id.jbase_swipe);
    }


    @Override protected boolean requestNoTitleBar() {
        return true;
    }
}

