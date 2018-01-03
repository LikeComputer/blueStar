package com.n4399.miniworld.vp.me.usercent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blueprint.Consistent;
import com.blueprint.helper.LogHelper;
import com.blueprint.helper.PicHelper;
import com.blueprint.helper.SpanHelper;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.AccountManage;
import com.n4399.miniworld.data.bean.AccountBean;
import com.n4399.miniworld.vp.basic.JBaseActivity;
import com.n4399.miniworld.vp.me.mycoll.MyCollectAt;
import com.n4399.miniworld.vp.me.usercent.share.UserShareListAt;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.blueprint.LibApp.setTextView;

public class UserCenterActivity extends JBaseActivity {

    @BindView(R.id.me_user_icon) ImageView meUserIcon;
    @BindView(R.id.me_user_name) TextView meUserName;
    @BindView(R.id.me_center_stars) TextView meCenterStars;
    @BindView(R.id.me_center_flowers) TextView meCenterFlowers;
    private AccountBean mUserBean;

    public static void start(Activity activity, AccountBean account, ImageView avatar){
        Intent intent = new Intent(activity, UserCenterActivity.class);
        intent.putExtra(Consistent.BUND_TAG, account);
        ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, avatar, ViewCompat.getTransitionName(avatar)).toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        mUserBean = getIntent().getParcelableExtra(Consistent.BUND_TAG);
        LogHelper.Log_d(mUserBean.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_center);
        ButterKnife.bind(this);
        configUserMsg();
    }

    private void configUserMsg(){
        ViewCompat.setTransitionName(meUserIcon, Consistent.TransitionName.TRANS_AVATAR);
        PicHelper.loadImage(mUserBean.getAvatar(), meUserIcon);
        setTextView(meUserName, mUserBean.getNickname());
        SpanHelper.getSpanModify(meCenterStars, "[0-9]*", R.style.me_center_nornum, R.style.me_center_keynum,
                R.string.me_center_fances, mUserBean.getFans());
        SpanHelper.getSpanModify(meCenterFlowers, "[0-9]*", R.style.me_center_nornum, R.style.me_center_keynum,
                R.string.me_center_follow, mUserBean.getFollow());

    }

    public void iconBack(View view){
        onBackPressed();
    }

    @OnClick({R.id.me_user_icon, R.id.me_login_mini_msg, R.id.me_center_stars, R.id.me_center_flowers, R.id.me_my_collection, R.id.me_my_share})
    public void onViewClicked(View view){
        switch(view.getId()) {
            case R.id.me_user_icon:
                if(AccountManage.getSingleton().getUserBean().equals(mUserBean)) {
                    toChangeAvatar();
                }
                break;
            case R.id.me_login_mini_msg:
                break;
            case R.id.me_center_stars:
                StarsFollowsAt.start(UserCenterActivity.this, StarsFollowsAt.FANS);
                break;
            case R.id.me_center_flowers:
                StarsFollowsAt.start(UserCenterActivity.this, StarsFollowsAt.FOLLOW);
                break;
            case R.id.me_my_collection:
                MyCollectAt.start(this, mUserBean);
                break;
            case R.id.me_my_share:
                UserShareListAt.start(this, mUserBean);
                break;
        }
    }

    private void toChangeAvatar(){
    }
}
