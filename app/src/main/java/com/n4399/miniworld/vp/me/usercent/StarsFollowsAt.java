package com.n4399.miniworld.vp.me.usercent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.Consistent;
import com.blueprint.LibApp;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.widget.JEditText;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.GuysBean;
import com.n4399.miniworld.vp.jpublic.BaseSearchListAt;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [关注和粉丝]
 */
public class StarsFollowsAt extends BaseSearchListAt<GuysBean> implements StarsFollowsContract.View<GuysBean>, JEditText.OnContentClearListener{

    public static final String FANS = "粉丝";
    public static final String FOLLOW = "关注";
    private String mType = FANS;
    private StarsFollowsPresenter mPresenter;

    public static void start(Activity activity, String type){
        Intent intent = new Intent(activity, StarsFollowsAt.class);
        intent.putExtra(Consistent.BUND_TAG, type);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        mType = getIntent().getStringExtra(Consistent.BUND_TAG);
        super.onCreate(savedInstanceState);
    }

    public void configViews(){
        super.configViews();
        if(FOLLOW.equals(mType)) {
            actRaiderSynthesSearchEtKey.setHint(LibApp.findString(R.string.me_center_search_stars));
        }else {
            actRaiderSynthesSearchEtKey.setHint(LibApp.findString(R.string.me_center_search_follows));
        }
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new StarsFollowsPresenter(this);
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration(0);
    }

    @Override
    protected String setTitle(){
        return mType;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        mCommonRecv.setPadding(0, mCommonRecv.getPaddingTop(), 0, 0);
        return new LinearLayoutManager(getApplicationContext());
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(GuysBean.class, new SimpleRecvBinder(R.layout.item_me_guys));
    }

    @Override
    public void showSucceed(List<GuysBean> data){
        List<GuysBean> mListData = new ArrayList<>();
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