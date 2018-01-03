package com.n4399.miniworld.vp.workshop.recommend;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.data.bean.RecomWormBean;
import com.n4399.miniworld.data.bean.Show2Bean;
import com.n4399.miniworld.data.bean.UnstableBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;
import com.n4399.miniworld.vp.holderbinder.ImagesBinder;
import com.n4399.miniworld.vp.holderbinder.ItemChangeMoudleBinder;
import com.n4399.miniworld.vp.workshop.recommend.walfare.WalfareAt;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class RecomFrgmt extends JAbsListFrgmt<Object,Object> implements RecomContract.View, OnItemClickListener<Object> {

    Unbinder unbinder;
    @BindView(R.id.frgmt_wshorp_recom_fb_startgame) ImageView frgmtWshorpRecomFbStartgame;
    private RecomPresenter mPresenter;

    public static RecomFrgmt getInstance(){
        return new RecomFrgmt();
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new RecomPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        //ButterKnife.bind(this, rootView);
        //configViews();
        mCommonRecv.setPadding(0, mCommonRecv.getPaddingTop(), 0, 0);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public int setCustomLayout(){
        return R.layout.frgmt_workshop_recom;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new DividerItemDecoration(getContext(), 0);
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(RecomWormBean.class, new SimpleRecvBinder(this, R.layout.recv_item_recom_msgcard));
        multiTypeAdapter.register(Show2Bean.class, new ImagesBinder(this));
        multiTypeAdapter.register(UnstableBean.class, new ItemChangeMoudleBinder(this));
    }

    @Override
    public void addLoopImageHolder(Show2Bean show2Bean){
        mListData.add(0, show2Bean);
    }

    @Override
    public void addUpdateHolder(List<RecomWormBean> wormBeanList){
        mListData.addAll(wormBeanList);
    }

    @Override
    public void addHotHolder(List<UnstableBean> unstableBeanList){
        mListData.addAll(unstableBeanList);
    }

    @Override
    public void addUnstableHolder(List<UnstableBean> unstableBeanList){
        mListData.addAll(unstableBeanList);
    }

    @Override
    public void showSucceed(List<Object> data){
        mMultiStateLayout.showStateSucceed();
        mRecvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.frgmt_wshorp_recom_fb_startgame)
    public void onViewClicked(){
        Toast.makeText(getContext(), "启动游戏", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(Object itemData, int position){
        //        HotMapBean
        if(itemData instanceof RecomWormBean) {
            RecomWormBean recomWormBean = (RecomWormBean)itemData;
            if(recomWormBean.getType() == RecomWormBean.TYPE_APP) {
                //版本刚更新/下载  都是下载
                //recomWormBean.getMiniApp().getDownloadUrl();
                Toast.makeText(getContext(), "下载", Toast.LENGTH_SHORT).show();
            }else {
                //todo 进入福利中心
                WalfareAt.start(getActivity());
            }
        }else if(itemData instanceof HotMapBean) {
            HotMapBean hotMapBean = (HotMapBean)itemData;
            //精选地图  进入详情
//            MapDetailAt.start(getActivity(),hotMapBean);
            System.out.println("================");
        }else if(itemData instanceof String) {
            //插卡跳转

        }else if(itemData instanceof Show2Bean.ShowBean) {
            //两个图片

        }
    }

    @Override
    public boolean setEnableSwipeRefresh(){
        return true;
    }
}