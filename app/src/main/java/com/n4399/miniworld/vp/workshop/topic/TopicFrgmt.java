package com.n4399.miniworld.vp.workshop.topic;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.helper.DpHelper;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.WStopic;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [专题]
 */
public class TopicFrgmt extends JAbsListFrgmt<WStopic,WStopic> {

    public static TopicFrgmt getInstance(){
        return new TopicFrgmt();
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return new TopicPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        mCommonRecv.setPadding(0, 0, 0, 0);
        return new LinearLayoutManager(getContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration((int)DpHelper.dp2px(1));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(WStopic.class, new SimpleRecvBinder<WStopic>(R.layout.item_ws_topic));
    }
}