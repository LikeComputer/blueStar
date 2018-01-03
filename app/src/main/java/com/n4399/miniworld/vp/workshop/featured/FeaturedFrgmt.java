package com.n4399.miniworld.vp.workshop.featured;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.blueprint.LibApp;
import com.blueprint.adapter.BaseRecycleAdapter;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.data.bean.WSfeature;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;
import com.n4399.miniworld.vp.workshop.featured.sort.MapSortAt;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.drakeet.multitype.MultiTypeAdapter;

import static com.blueprint.helper.DpHelper.dp2px;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [精选]
 */
public class FeaturedFrgmt extends JAbsListFrgmt<HotMapBean,Object> implements OnItemClickListener, FeatureContract.View, AdapterView.OnItemSelectedListener {
    FeaturePresenter mPresenter;
    @BindView(R.id.frgmt_wshorp_feature_sort) RecyclerView frgmtWshorpFeatureSort;
    @BindView(R.id.item_fixinterval_tv) TextView itemFixintervalTv;
    @BindView(R.id.frgmt_wshorp_feature_sort_span) Spinner mSortSpan;
    Unbinder unbinder;
    private BaseRecycleAdapter mSortAdapter;
    private ArrayList<WSfeature.TypelistBean> mSortList;

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new FeaturePresenter(this);
    }

    @Override
    public int setCustomLayout(){
        return R.layout.frgmt_workshop_feature;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        mSortAdapter = new BaseRecycleAdapter<WSfeature.TypelistBean>(R.layout.item_ws_feature_sort) {
            @Override
            public void convert(final RecyclerHolder holder, int position, final WSfeature.TypelistBean itemData){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        toSortDetail(itemData,holder.getAdapterPosition());
                    }
                });
            }
        };
        frgmtWshorpFeatureSort.setLayoutManager(new GridLayoutManager(getContext(), 4));
        frgmtWshorpFeatureSort.setAdapter(mSortAdapter);

        mSortSpan.setAdapter(ArrayAdapter
                .createFromResource(getContext(), R.array.frgmt_workshop_mapsort, R.layout.simple_item_list_tv));
        mSortSpan.setOnItemSelectedListener(this);
        return rootView;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration((int)dp2px(1));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(HotMapBean.class, new SimpleRecvBinder(R.layout.item_ws_map));
    }

    @Override
    public void onItemClicked(Object itemData, int position){
        if(itemData instanceof HotMapBean) {
            HotMapBean hotmap = (HotMapBean)itemData;

        }else {
            //分类
        }
    }

    @Override
    public void enAbleLoadMore(boolean enable){
        super.enAbleLoadMore(enable);
    }

    private void toSortDetail(WSfeature.TypelistBean itemData, int position){
        MapSortAt.startActivity(getActivity(), mSortList, position);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showSortGride(ArrayList<WSfeature.TypelistBean> sortList){
        mSortList = sortList;
        mSortAdapter.notifyDataChange(sortList);
    }

    @Override
    public void showFeatureMapList(List<HotMapBean> mapList){
        mRecvAdapter.refreshAllData(mapList);
    }

    @Override
    public void showSucceed(List<Object> data){
        super.showSucceed(data);
    }

    @Override
    public void notifySortChange(){
        mRecvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        ( (TextView)view ).setTextColor(LibApp.findColor(R.color.colorAccent));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }
}
