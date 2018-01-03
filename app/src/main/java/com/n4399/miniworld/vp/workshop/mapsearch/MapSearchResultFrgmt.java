package com.n4399.miniworld.vp.workshop.mapsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.data.event.SearchEvent;
import com.n4399.miniworld.vp.basic.BaseSearchResultFrgmt;

import me.drakeet.multitype.MultiTypeAdapter;

import static com.blueprint.helper.DpHelper.dp2px;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MapSearchResultFrgmt extends BaseSearchResultFrgmt<HotMapBean,HotMapBean> {

    private MapSearchResultPresenter mPresenter;

    public static MapSearchResultFrgmt getInstance(){
        return new MapSearchResultFrgmt();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState, true);
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new MapSearchResultPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        mCommonRecv.setPadding(0, 0, 0, 0);
        return new LinearLayoutManager(getContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration((int)dp2px(1));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(HotMapBean.class,
                new SimpleRecvBinder(R.layout.item_ws_map));
    }

    @Override
    public void toDoSearch(SearchEvent searchEvent){
        mPresenter.search(searchEvent.search_key);
    }

}