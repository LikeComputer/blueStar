package com.blueprint.basic.frgmt;

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
public abstract class JAbsListFrgmt<VM extends LoadMoreObjectViewModel> extends JBaseVMFrgmt<VM> {

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override protected int resetFrgmtLayoutRes() {
        return R.layout.jbasic_abslist_swipe_layout;
    }


    @Override
    protected int setContentLayoutId(){
        return 0;
    }

    @Override protected void findStatbleViews(View rootView) {
        mContentView = rootView.findViewById(R.id.jbase_list_content);
        mToolBar = rootView.findViewById(R.id.jbase_toolbar);
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


    @Override protected int setRootVariableId() {
        return BR.pageViewModel;
    }


    @Override protected void toSubscribeData(VM viewModel) {}
}
