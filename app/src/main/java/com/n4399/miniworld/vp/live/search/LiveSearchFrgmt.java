package com.n4399.miniworld.vp.live.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.LibApp;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.GrideDividerDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.LiveIngBean;
import com.n4399.miniworld.data.event.SearchEvent;
import com.n4399.miniworld.vp.basic.BaseSearchResultFrgmt;
import com.n4399.miniworld.vp.basic.JBasicWebViewAt;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class LiveSearchFrgmt extends BaseSearchResultFrgmt<LiveIngBean,LiveIngBean> implements OnItemClickListener<LiveIngBean> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState, true);
    }

    public static LiveSearchFrgmt getInstance(){
        return new LiveSearchFrgmt();
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return new LiveSearchPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new GrideDividerDecoration(LibApp.findDimens(R.dimen.card_pading_8));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(LiveIngBean.class, new SimpleRecvBinder(this,R.layout.recv_item_live_ing));
    }

    @Override
    public void onItemClicked(LiveIngBean itemData, int position){
        JBasicWebViewAt.start(getActivity(), itemData.getUrl());
    }

    @Override
    public void toDoSearch(SearchEvent searchEvent){
        mGeneralListPresenter.search(searchEvent.search_key);
    }

    @Override
    public void showSucceed(List<LiveIngBean> data){
        mListData = new ArrayList<>();
        //两部分数据
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        super.showSucceed(mListData);
    }
}