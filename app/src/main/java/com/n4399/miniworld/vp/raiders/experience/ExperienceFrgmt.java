package com.n4399.miniworld.vp.raiders.experience;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.MsgCardBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;
import com.n4399.miniworld.vp.holderbinder.ItemMsgCardBinder;

import java.util.List;

import april.yun.JPagerSlidingTabStrip;
import jonas.jlayout.MultiStateLayout;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [攻略心得 SearchResultFrgmt做实现]
 */
public class ExperienceFrgmt extends JAbsListFrgmt implements ItemMsgCardBinder.MsgCardItemListener {


    private MultiStateLayout mStateLayout;

    @Override
    protected GeneralListPresenter initListPresenter(){
        return null;
    }

    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout container){
        super.onCreateContent(inflater, container);
        mStateLayout = (MultiStateLayout)container.findViewById(R.id.fm_raiders_experience_multistate_layout);
        JPagerSlidingTabStrip tabStrip = (JPagerSlidingTabStrip)container.findViewById(R.id.fm_raiders_psts_tab);

        tabStrip.getTabStyleDelegate().setShouldExpand(true).setFrameColor(Color.parseColor("#45C01A"))
                .setCornerRadio(40).setIndicatorHeight(80).setIndicatorColor(Color.parseColor("#45C01A"))
                .setTextColor(Color.parseColor("#45C01A"), Color.GRAY);
        tabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

            }

            @Override
            public void onPageSelected(int position){
            }

            @Override
            public void onPageScrollStateChanged(int state){

            }
        });

        //        tabStrip.bind;
    }

    @Override
    public int setCustomLayout(){
        return R.layout.fm_raiders_experience;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(MsgCardBean.class, new ItemMsgCardBinder(this));
//        multiTypeAdapter.register(MapseekBean.class, new ItemMapSeekBinder());
    }

    @Override
    public void onMsgCardItemClicked(MsgCardBean item, int position){

    }

    @Override
    public void showLoading(){
        mStateLayout.showStateLoading();
    }

    @Override
    public void showSucceed(Object data){
        mStateLayout.showStateSucceed();
    }

    @Override
    public void onMoreLoad(List data){
        mListData.addAll(data);
        mRecvAdapter.notifyDataSetChanged();
    }
}
