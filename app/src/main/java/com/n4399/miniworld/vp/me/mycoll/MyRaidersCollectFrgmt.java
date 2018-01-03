package com.n4399.miniworld.vp.me.mycoll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.MsgCardBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;
import com.n4399.miniworld.vp.basic.JBasicWebViewAt;
import com.n4399.miniworld.vp.holderbinder.ItemMsgCardBinder;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [攻略收藏]
 */
public class MyRaidersCollectFrgmt extends JAbsListFrgmt<MsgCardBean,MsgCardBean> implements ItemMsgCardBinder.MsgCardItemListener {

    private MyRaidersCollectPresenter mPresenter;

    public static MyRaidersCollectFrgmt getInstance(){
        return new MyRaidersCollectFrgmt();
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new MyRaidersCollectPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration(0);
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(MsgCardBean.class,new ItemMsgCardBinder(this));
    }

    @Override
    public void onMsgCardItemClicked(MsgCardBean item, int position){
        JBasicWebViewAt.start(getActivity(), item.getUrl());
    }

    @Override
    public void showSucceed(List<MsgCardBean> data){
        data = new ArrayList<>();
        data.add(new MsgCardBean("测试"));
        data.add(new MsgCardBean("测试"));
        data.add(new MsgCardBean("测试"));
        data.add(new MsgCardBean("测试"));
        data.add(new MsgCardBean("测试"));
        data.add(new MsgCardBean("测试"));
        super.showSucceed(data);
    }
}