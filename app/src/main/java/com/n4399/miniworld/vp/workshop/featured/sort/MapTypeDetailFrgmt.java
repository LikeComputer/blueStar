package com.n4399.miniworld.vp.workshop.featured.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blueprint.Consistent;
import com.blueprint.LibApp;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.helper.DpHelper;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;

import april.yun.JPagerSlidingTabStrip;
import april.yun.other.JTabStyleDelegate;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.drakeet.multitype.MultiTypeAdapter;

import static com.blueprint.LibApp.findColor;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MapTypeDetailFrgmt extends JAbsListFrgmt<HotMapBean,HotMapBean> implements
        MapTypeDetailContract.View{

    @BindView(R.id.frgmt_wshorp_feature_sortdetail_sort) JPagerSlidingTabStrip frgmtWshorpFeatureSortdetailSort;
    @BindArray(R.array.frgmt_workshop_mapsort) String[] mSorts;
    Unbinder unbinder;
    private MapTypeDetailPresenter mPresenter;
    private int mTypeId;

    public static MapTypeDetailFrgmt getInstance(int id){
        MapTypeDetailFrgmt mapTypeDetailFrgmt = new MapTypeDetailFrgmt();
        Bundle bundle = new Bundle();
        bundle.putInt(Consistent.BUND_TAG, id);
        mapTypeDetailFrgmt.setArguments(bundle);
        return mapTypeDetailFrgmt;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        mTypeId = getArguments().getInt(Consistent.BUND_TAG);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new MapTypeDetailPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initTabStrip(frgmtWshorpFeatureSortdetailSort);
        frgmtWshorpFeatureSortdetailSort.bindTitles(mSorts);
        frgmtWshorpFeatureSortdetailSort.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

            }

            @Override
            public void onPageSelected(int position){
                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state){

            }
        });
        return rootView;
    }

    private void initTabStrip(JPagerSlidingTabStrip mSecTabStrip){
        //        2，拿TabStyleDelegate
        JTabStyleDelegate tabStyleDelegate = mSecTabStrip.getTabStyleDelegate();
        //        3, 用TabStyleDelegate设置属性
        tabStyleDelegate.setCornerRadio(100)
                //也可以直接传字符串的颜色，第一个颜色表示checked状态的颜色第二个表示normal状态
                .setTextColor(findColor(R.color.colorPrimary), findColor(R.color.gray999))
                .setTabTextSize((int)DpHelper.dp2px(12))
                .setFrameColor(LibApp.findColor(R.color.common_line));
    }

    @Override
    public int setCustomLayout(){
        return R.layout.frgmt_map_type_detail;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        mCommonRecv.setPadding(0, mCommonRecv.getPaddingTop(), 0, 0);
        return new LinearLayoutManager(getContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration((int)DpHelper.dp2px(1));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(HotMapBean.class, new SimpleRecvBinder(R.layout.item_ws_map));
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }
}