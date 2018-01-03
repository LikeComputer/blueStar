package com.n4399.miniworld.vp.me.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.GuysBean;
import com.n4399.miniworld.data.event.SearchEvent;
import com.n4399.miniworld.vp.basic.BaseSearchResultFrgmt;
import com.n4399.miniworld.vp.me.mycoll.MyCollectAt;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class GuysSearchResultFrgmt extends BaseSearchResultFrgmt<GuysBean,GuysBean> {

    private GuysSearchResultPresenter mPresenter;

    public static GuysSearchResultFrgmt getInstance(){
        return new GuysSearchResultFrgmt();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState, true);
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new GuysSearchResultPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        mCommonRecv.setPadding(0, mCommonRecv.getPaddingTop(), 0, 0);
        multiTypeAdapter.register(GuysBean.class, new SimpleRecvBinder(R.layout.item_me_guys));
    }

    @Override
    public void toDoSearch(SearchEvent searchEvent){
        mGeneralListPresenter.search(searchEvent.search_key);
    }

    @Override
    public void showSucceed(List<GuysBean> data){
        mListData = new ArrayList<>();
        mListData.add(new GuysBean());
        mListData.add(new GuysBean());
        mListData.add(new GuysBean());
        mListData.add(new GuysBean());
        mListData.add(new GuysBean());
        mListData.add(new GuysBean());
        mListData.add(new GuysBean());
        mListData.add(new GuysBean());
        mListData.add(new GuysBean());
        mListData.add(new GuysBean());
        mListData.add(new GuysBean());
        super.showSucceed(mListData);
    }
}