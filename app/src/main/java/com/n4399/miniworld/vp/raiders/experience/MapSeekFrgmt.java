package com.n4399.miniworld.vp.raiders.experience;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.LibApp;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.GrideDividerDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.MapseekBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;
import com.n4399.miniworld.vp.webactivity.WebViewPage;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [地图种子]
 */
public class MapSeekFrgmt extends JAbsListFrgmt<MapseekBean,MapseekBean> implements OnItemClickListener<MapseekBean> {
    public static MapSeekFrgmt getInstance(){
        return new MapSeekFrgmt();
    }

    @Override
    protected GeneralListPresenter<MapseekBean> initListPresenter(){
        return new MapSeekPresenter(this);
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
        multiTypeAdapter.register(MapseekBean.class, new SimpleRecvBinder(this, R.layout.recv_item_raiders_mapseek));
    }

    @Override
    public void onItemClicked(MapseekBean itemData, int position){
        WebViewPage.start(getActivity(), itemData.getUrl());
    }

    @Override
    public void showSucceed(List<MapseekBean> data){
        mListData = new ArrayList<>();
        mListData.add(new MapseekBean());
        mListData.add(new MapseekBean());
        mListData.add(new MapseekBean());
        mListData.add(new MapseekBean());
        mListData.add(new MapseekBean());
        mListData.add(new MapseekBean());
        mListData.add(new MapseekBean());
        mListData.add(new MapseekBean());
        super.showSucceed(mListData);
    }
}