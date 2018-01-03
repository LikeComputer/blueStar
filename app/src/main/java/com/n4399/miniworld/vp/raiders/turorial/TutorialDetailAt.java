package com.n4399.miniworld.vp.raiders.turorial;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.Consistent;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.MsgCardBean;
import com.n4399.miniworld.data.bean.TutorialBean;
import com.n4399.miniworld.vp.basic.JAbsListActivity;
import com.n4399.miniworld.vp.basic.JBasicWebViewAt;
import com.n4399.miniworld.vp.holderbinder.ItemMsgCardBinder;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * [游戏教程 详情]
 */
public class TutorialDetailAt extends JAbsListActivity<MsgCardBean,MsgCardBean> implements ItemMsgCardBinder.MsgCardItemListener {

    public static void start(Activity activity, TutorialBean itemData){
        Intent intent = new Intent(activity, TutorialDetailAt.class);
        intent.putExtra(Consistent.BUND_TAG, itemData.getTitle());
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected boolean requestNoTitleBar(){
        return false;
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration(0);
    }

    @Override
    protected String setTitle(){
        return getIntent().getStringExtra(Consistent.BUND_TAG);
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return new TutorialDetailPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new LinearLayoutManager(getApplicationContext());
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(MsgCardBean.class, new ItemMsgCardBinder(this));
    }

    @Override
    public void onMsgCardItemClicked(MsgCardBean item, int position){
        JBasicWebViewAt.start(this, "http://192.168.54.245:90/index.php?s=/5&page_id=132");
    }

    @Override
    public void showSucceed(List<MsgCardBean> data){
        mListData = new ArrayList<>();
        mListData.add(new MsgCardBean("测"));
        mListData.add(new MsgCardBean("测"));
        mListData.add(new MsgCardBean("测"));
        mListData.add(new MsgCardBean("测"));
        super.showSucceed(mListData);
    }
}