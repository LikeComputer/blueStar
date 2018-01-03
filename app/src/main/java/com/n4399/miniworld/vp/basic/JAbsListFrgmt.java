package com.n4399.miniworld.vp.basic;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.blueprint.adapter.LoadMoreWrapperDampAdapter;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListContract;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.basic.JBasePresenter;
import com.n4399.miniworld.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import me.drakeet.multitype.MultiTypeAdapter;

import static com.blueprint.Consistent.ErrorCode.ERROR_EMPTY;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [推荐]
 * <android.support.v7.widget.RecyclerView
 * android:id="@+id/common_recv"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"
 * android:clipToPadding="false"
 * android:paddingLeft="14dp"
 * android:paddingRight="14dp"
 * android:paddingTop="8dp">
 */
public abstract class JAbsListFrgmt<T, D> extends JBaseTitleFrgmt implements GeneralListContract.View<T,D>, SwipeRefreshLayout.OnRefreshListener, LoadMoreWrapperDampAdapter.OnMoreloadListener {
    public RecyclerView mCommonRecv;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public List<T> mListData = new ArrayList();
    public LoadMoreWrapperDampAdapter mRecvAdapter;
    public GeneralListPresenter mGeneralListPresenter;

    @Override
    protected final JBasePresenter initPresenter(){
        return mGeneralListPresenter = initListPresenter();
    }

    protected abstract GeneralListPresenter initListPresenter();

    @Override
    protected boolean requestNoTitleBar(){
        return true;
    }

    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout container){
        View rootView;
        if(setCustomLayout() == -10) {
            rootView = inflater.inflate(R.layout.common_abslist, container);
        }else {
            rootView = inflater.inflate(setCustomLayout(), container);
        }
        mCommonRecv = (RecyclerView)rootView.findViewById(R.id.jbase_recv);
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.jbase_swipe);
        ButterKnife.bind(rootView);
        initRecView();
        initSwipeLayout();
    }

    public int setCustomLayout(){
        return -10;
    }

    private void initSwipeLayout(){
        mSwipeRefreshLayout.setEnabled(setEnableSwipeRefresh());
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initRecView(){
        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter(mListData);
        mCommonRecv.setLayoutManager(setLayoutManager());
        mCommonRecv.addItemDecoration(setItemDecoration());
        register2Adapter(multiTypeAdapter);
        mRecvAdapter = new LoadMoreWrapperDampAdapter<T>(multiTypeAdapter) {

            @Override
            public boolean enableUpMore(){
                return setEnableUpMore();
            }

            @Override
            public RecyclerHolder onCreateLoadingHolder(ViewGroup parent){
                return setLoadingHolder(parent);
            }

            @Override
            public void loadError(){
                setLoadError();
            }

            @Override
            public void enAbleLoadMore(boolean enable){
                super.enAbleLoadMore(enable);//无数据 自定义底布局
            }
        };
        mRecvAdapter.setPagesize(setPageSize());
        mRecvAdapter.setOnMoreloadListener(this);
        mCommonRecv.setAdapter(mRecvAdapter);
    }

    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration(1);
    }

    public int setPageSize(){
        return 10;
    }

    public void setLoadError(){
        mRecvAdapter.loadError();
    }

    protected RecyclerHolder setLoadingHolder(ViewGroup parent){
        return null;
    }

    protected boolean setEnableUpMore(){
        return false;
    }


    protected abstract RecyclerView.LayoutManager setLayoutManager();

    protected abstract void register2Adapter(MultiTypeAdapter multiTypeAdapter);

    @Override
    public void onRefresh(){
        if(mGeneralListPresenter != null) {
            mGeneralListPresenter.down2RefreshData(mListData);
        }
    }


    public boolean setEnableSwipeRefresh(){
        return false;
    }

    @Override
    public void onLoadingMore(){
        if(mGeneralListPresenter != null) {
            mGeneralListPresenter.up2LoadMoreData(mListData);
        }
    }

    @Override
    public void onMoreLoad(List<T> moreData){
        mRecvAdapter.addMoreList(moreData);
    }

    @Override
    public void enAbleLoadMore(boolean enable){
        mRecvAdapter.enAbleLoadMore(enable);
    }

    @Override
    public void showSucceed(List<D> data){
        mRecvAdapter.refreshAllData(data);
        mMultiStateLayout.showStateSucceed();
    }

    @Override
    public void showLoading(){
        mMultiStateLayout.showStateLoading();
    }

    @Override
    public void showError(int eCode){
        if(eCode == ERROR_EMPTY) {
            mMultiStateLayout.showStateEmpty();
        }else {
            mMultiStateLayout.showStateError();
        }
    }
}