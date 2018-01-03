package com.n4399.miniworld.vp.me.mycoll;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.GrideDividerDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.LiveIngBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;
import com.n4399.miniworld.vp.basic.JBasicWebViewAt;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MyVideoCollectFrgmt extends JAbsListFrgmt<LiveIngBean,LiveIngBean> implements OnItemClickListener<LiveIngBean> {

    private MyVideoCollectPresenter mPresenter;

    public static MyVideoCollectFrgmt getInstance(){
        return new MyVideoCollectFrgmt();
    }

    @Override
    protected GeneralListPresenter<LiveIngBean> initListPresenter(){
        return mPresenter = new MyVideoCollectPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new GridLayoutManager(getContext(),2);
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new GrideDividerDecoration(0);
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(LiveIngBean.class, new SimpleRecvBinder(this,R.layout.recv_item_live_ing));
    }

    @Override
    public void onItemClicked(LiveIngBean itemData, int position){
        JBasicWebViewAt.start(getActivity(),itemData.getUrl());
    }

    @Override
    public void showSucceed(List<LiveIngBean> data){
        data = new ArrayList<>();
        data.add(new LiveIngBean());
        data.add(new LiveIngBean());
        data.add(new LiveIngBean());
        data.add(new LiveIngBean());
        data.add(new LiveIngBean());
        data.add(new LiveIngBean());
        data.add(new LiveIngBean());
        data.add(new LiveIngBean());
        data.add(new LiveIngBean());
        data.add(new LiveIngBean());
        data.add(new LiveIngBean());
        super.showSucceed(data);
    }
}