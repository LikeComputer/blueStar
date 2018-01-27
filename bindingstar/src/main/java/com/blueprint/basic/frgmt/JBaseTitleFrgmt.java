package com.blueprint.basic.frgmt;

import android.view.View;

import com.blueprint.R;

import jzy.easybindpagelist.statehelper.BaseDiffSteteViewModel;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [标题+状态界面  有统一处理 basePresenter的subscribe和unsubscribe]
 * onCreate中获取 getArguments数据
 */
public abstract class JBaseTitleFrgmt<VM extends BaseDiffSteteViewModel>
        extends JBaseVMFrgmt<VM> {

    @Override protected boolean requestNoTitleBar() {
        return true;
    }


    @Override protected int resetFrgmtLayoutRes() {
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
}
