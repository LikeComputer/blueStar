package com.blueprint.basic.activity;

import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import com.blueprint.BR;
import com.blueprint.R;
import jzy.easybindpagelist.statehelper.BaseDiffSteteViewModel;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [标题+状态界面  有统一处理 basePresenter的subscribe和unsubscribe]
 */
public abstract class JBaseTitleStateActivity<VM extends BaseDiffSteteViewModel, DBI extends ViewDataBinding>
        extends JBaseVMActivity<VM, DBI> {
    protected SwipeRefreshLayout mSwipeRefreshLayout;


    @Override protected void findStatbleViews(View rootView) {
        super.findStatbleViews(rootView);
        mLayoutBelowToolbar = mSwipeRefreshLayout = rootView.findViewById(R.id.jbase_swipe);
    }


    /**
     * 最外层 viewdatabinding的BR_id
     */
    protected int setRootVariableId() {
        return BR.jbaseStateViewModel;
    }


    @Override protected int resetActLayoutRes() {
        return R.layout.jbasic_state_swipe_layout;
    }
}