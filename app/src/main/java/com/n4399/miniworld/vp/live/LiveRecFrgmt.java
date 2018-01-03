package com.n4399.miniworld.vp.live;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blueprint.Consistent;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.GrideDividerDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.helper.DpHelper;
import com.blueprint.widget.LoopImagePager;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.LiveIngBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;
import com.n4399.miniworld.vp.basic.JBasicWebViewAt;
import com.n4399.miniworld.vp.live.sort.RecoSortAt;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.MultiTypeAdapter;

import static com.blueprint.LibApp.findDimens;

/**
 * @another [https://github.com/ZuYun]
 * @desc [直播和推荐公用]
 */
public class LiveRecFrgmt extends JAbsListFrgmt<LiveIngBean,LiveIngBean> implements OnItemClickListener<LiveIngBean>, View.OnClickListener, LoopImagePager.onLoopImageClickListener {

    public static final int LIVE_ING = 1;
    public static final int LIVE_RECO = 2;
    private int mLiveType = LIVE_ING;

    @BindView(R.id.frgmt_liveing_lip) LoopImagePager frgmtLiveingLip;
    @BindView(R.id.fm_live_top_layout) RelativeLayout mRelativeLayout;
    @BindView(R.id.frgmt_liveing_cl) CoordinatorLayout frgmtLiveingCl;
    @BindView(R.id.jbase_recv) RecyclerView commonRecv;
    @BindView(R.id.fm_live_reco_xxyl) TextView fmLiveRecoXxyl;
    @BindView(R.id.fm_live_reco_dljx) TextView fmLiveRecoDljx;
    @BindView(R.id.fm_live_reco_scsk) TextView fmLiveRecoScsk;
    @BindView(R.id.fm_live_reco_jzjx) TextView fmLiveRecoJzjx;
    @BindView(R.id.frgmt_liveing_ll_) LinearLayout frgmtLiveingLl;
    @BindView(R.id.frgmt_liveing_tv_jx) TextView frgmtLiveingTvJx;
    @BindView(R.id.jbase_swipe) SwipeRefreshLayout commonSwipe;
    private RelativeLayout mTopView;
    private float mTopPading;
    private int mTopOffset;

    public static LiveRecFrgmt getInstance(int liveType){
        LiveRecFrgmt liveRecFrgmt = new LiveRecFrgmt();
        Bundle args = new Bundle();
        args.putInt(Consistent.BUND_TAG, liveType);
        liveRecFrgmt.setArguments(args);
        return liveRecFrgmt;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Bundle arguments = getArguments();
        if(arguments != null) {
            mLiveType = arguments.getInt(Consistent.BUND_TAG);
        }
    }

    @Override
    protected GeneralListPresenter<LiveIngBean> initListPresenter(){
        return new GeneralListPresenter(this);
    }

    @Override
    public int setCustomLayout(){
        return R.layout.fgrmt_liveing_with_loopimage;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new GrideDividerDecoration(findDimens(R.dimen.card_pading_8));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(LiveIngBean.class, new SimpleRecvBinder(this,R.layout.recv_item_live_ing));
    }


    @Override
    public void onItemClicked(LiveIngBean itemData, int position){
        JBasicWebViewAt.start(getActivity(), itemData.getUrl());
    }

    @Override
    public void showSucceed(List<LiveIngBean> data){
        List<String> urls = new ArrayList<>();
        urls.add("https://ws1.sinaimg.cn/large/610dc034ly1fgllsthvu1j20u011in1p.jpg");
        urls.add("https://ws1.sinaimg.cn/large/610dc034ly1fgi3vd6irmj20u011i439.jpg");
        urls.add("https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
        frgmtLiveingLip.setPagerData(urls);
        mListData = new ArrayList<>();
        //两部分数据
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        super.showSucceed(mListData);

    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        mTopView = (RelativeLayout)frgmtLiveingLip.getParent();
        configTopView();
        for(int i = 0; i<frgmtLiveingLl.getChildCount(); i++) {
            frgmtLiveingLl.getChildAt(i).setOnClickListener(this);
            frgmtLiveingLl.getChildAt(i).setTag(i);//跳转到第几个页面
        }
        frgmtLiveingLip.setOnPagerItemClickListener(this);
        mCommonRecv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){

                mRelativeLayout.setTranslationY(mTopOffset += -dy);
            }
        });
        return rootView;
    }

    private void configTopView(){
        if(LIVE_ING == mLiveType) {
            mTopPading = DpHelper.dp2px(20);
            //直播类型 移除多余控件
            for(; mTopView.getChildCount()>1; ) {
                mTopView.removeViewAt(1);
            }
        }
        mCommonRecv.post(new Runnable() {
            @Override
            public void run(){
                mCommonRecv.setPadding(mCommonRecv.getPaddingLeft(), (int)( mTopView.getMeasuredHeight()+mTopPading ),
                        mCommonRecv.getPaddingRight(), mCommonRecv.getPaddingBottom());
            }
        });
    }

    @Override
    public void onClick(View v){
        RecoSortAt.start(getActivity(), ( (int)v.getTag() ));
    }

    @Override
    public void onItemClickd(int Position){
        Toast.makeText(getContext(), ""+Position, Toast.LENGTH_SHORT).show();
    }
}