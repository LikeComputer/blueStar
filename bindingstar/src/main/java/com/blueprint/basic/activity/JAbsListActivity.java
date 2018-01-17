package com.blueprint.basic.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.blueprint.BR;
import com.blueprint.R;
import jonas.jlayout.MultiStateLayout;
import jzy.easybindpagelist.loadmorehelper.LoadMoreObjectViewModel;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [推荐]
 */
public abstract class JAbsListActivity<VM extends LoadMoreObjectViewModel> extends JBaseVMActivity<VM, ViewDataBinding> {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override protected int resetActLayoutRes() {
        return R.layout.jbasic_abslist_swipe_layout;
    }


    @Override protected void findStatbleViews(View rootView) {
        mContentView = rootView.findViewById(R.id.jbase_list_content);
        mToolBar = rootView.findViewById(R.id.jbase_toolbar);
    }


    @Override protected ViewDataBinding onCreateContent(LayoutInflater layoutInflater, ViewGroup containerLayout) {
        return null;
    }


    @Override protected int setContentVariableId() {
        return 0;
    }


    public RecyclerView getRecycleView() {
        return mContentView.findViewById(R.id.jpage_recyv);
    }


    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mContentView.findViewById(R.id.jswipe_refrl);
    }


    public MultiStateLayout getStateLayout() {
        return mContentView.findViewById(R.id.joutter_state);
    }


    protected int setRootVariableId() {
        return BR.pageViewModel;
    }


    protected void toSubscribeData(VM viewModel) {
        viewModel.registLife(this);
    }
}
