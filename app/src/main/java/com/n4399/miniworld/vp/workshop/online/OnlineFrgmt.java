package com.n4399.miniworld.vp.workshop.online;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blueprint.LibApp;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.helper.DpHelper;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.RoomBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;
import com.n4399.miniworld.vp.workshop.online.seach.RoomSearchAt;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class OnlineFrgmt extends JAbsListFrgmt<RoomBean,RoomBean> implements OnlineContract.View, OnItemClickListener<RoomBean>, AdapterView.OnItemSelectedListener {

    @BindView(R.id.frgmt_wshorp_feature_sort_span) Spinner frgmtWshorpFeatureSortSpan;
    private OnlinePresenter mPresenter;

    public static OnlineFrgmt getInstance(){
        return new OnlineFrgmt();
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new OnlinePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        frgmtWshorpFeatureSortSpan.setAdapter(ArrayAdapter
                .createFromResource(getContext(), R.array.frgmt_workshop_mapsort, R.layout.simple_item_list_tv));
        frgmtWshorpFeatureSortSpan.setOnItemSelectedListener(this);
        return rootView;
    }

    @Override
    public int setCustomLayout(){
        return R.layout.frgmt_workshop_online;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
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
    public void showReSortedRoom(){
        mRecvAdapter.notifyDataSetChanged();
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        ( (TextView)view ).setTextColor(LibApp.findColor(R.color.colorAccent));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }

    @OnClick(R.id.raiders_titlebar_search)
    public void onViewClicked(){
        RoomSearchAt.start(getActivity());
    }
}