package com.n4399.miniworld.vp.me.usercent.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.Consistent;
import com.blueprint.LibApp;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.helper.DpHelper;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.AccountBean;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.vp.basic.JAbsListActivity;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [我的分享]
 */
public class UserShareListAt extends JAbsListActivity<HotMapBean,HotMapBean>{

    private UserSharePresenter mPresenter;
    private Parcelable mAccount;

    public static void start(Activity activity, AccountBean account){
        Intent intent = new Intent(activity, UserShareListAt.class);
        intent.putExtra(Consistent.BUND_TAG, account);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        mAccount = getIntent().getParcelableExtra(Consistent.BUND_TAG);
        return mPresenter = new UserSharePresenter(this);
    }

    @Override
    protected String setTitle(){
        return LibApp.findString(R.string.me_setting_center_share);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        mCommonRecv.setPadding(0, 0, 0, 0);
        return new LinearLayoutManager(getApplicationContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration((int)DpHelper.dp2px(1));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        //HotMapBean点击事件在HotMapBean中处理
        multiTypeAdapter.register(HotMapBean.class, new SimpleRecvBinder(R.layout.item_ws_map));
    }

}