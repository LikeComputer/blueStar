package com.n4399.miniworld.vp.workshop.online.seach;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.helper.DpHelper;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.RoomBean;
import com.n4399.miniworld.data.event.SearchEvent;
import com.n4399.miniworld.vp.basic.BaseSearchResultFrgmt;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class RoomSearchResultFrgmt extends BaseSearchResultFrgmt<RoomBean,RoomBean> implements OnItemClickListener<RoomBean> {

    private RoomSearchResultPresenter mPresenter;

    public static RoomSearchResultFrgmt getInstance(){
        return new RoomSearchResultFrgmt();
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new RoomSearchResultPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState, true);
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
        multiTypeAdapter.register(RoomBean.class, new SimpleRecvBinder<RoomBean>(this, R.layout.item_ws_online_room));
    }

    @Override
    public void onItemClicked(RoomBean itemData, int position){
        String ts = "";
        if(itemData.getOnlineNum()<itemData.getTotalNum()) {
            if(itemData.isLock()) {
                ts = "房间以上锁";
            }else {
                ts = "一键加入";
            }
        }else {
            ts = "房间已满";
        }
        Toast.makeText(getContext(), ts, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toDoSearch(SearchEvent searchEvent){
        mPresenter.search(searchEvent.search_key);
    }
}