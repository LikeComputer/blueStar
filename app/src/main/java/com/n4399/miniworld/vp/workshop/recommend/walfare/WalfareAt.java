package com.n4399.miniworld.vp.workshop.recommend.walfare;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.LibApp;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.FixIntervalBean;
import com.n4399.miniworld.data.bean.ItemLoopImage;
import com.n4399.miniworld.data.bean.RecomWormBean;
import com.n4399.miniworld.data.bean.WSwalfare;
import com.n4399.miniworld.vp.basic.JAbsListActivity;
import com.n4399.miniworld.vp.holderbinder.ItemLoopImageBinder;

import me.drakeet.multitype.MultiTypeAdapter;

import static com.blueprint.LibApp.getContext;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class WalfareAt extends JAbsListActivity<Object,Object> implements OnItemClickListener<Object> {

    private WalfarePresenter mPresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, WalfareAt.class);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void onAttachedToWindow(){
        mMultiStateLayout.postDelayed(new Runnable() {
            @Override
            public void run(){
                mPresenter.subscribe(null);
            }
        },3000);
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new WalfarePresenter(this);
    }

    @Override
    protected String setTitle(){
        return LibApp.findString(R.string.recom_welfare_center);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        mCommonRecv.setPadding(0, mCommonRecv.getPaddingTop(), 0, 0);
        return new LinearLayoutManager(getApplicationContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new DividerItemDecoration(getContext(), 0);
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(ItemLoopImage.class, new ItemLoopImageBinder(this));
        multiTypeAdapter.register(FixIntervalBean.class, new SimpleRecvBinder(R.layout.item_fix_interval));
        multiTypeAdapter.register(RecomWormBean.class, new SimpleRecvBinder(this, R.layout.recv_item_recom_msgcard));
        multiTypeAdapter
                .register(WSwalfare.ActivitysBean.class, new SimpleRecvBinder(this, R.layout.item_item_activitys));
    }

    @Override
    public void onItemClicked(Object itemData, int position){
        if(itemData instanceof RecomWormBean) {
            //礼包

        }else if(itemData instanceof WSwalfare.ActivitysBean) {
            //活动
        }else {
            //轮播图
        }
    }
}