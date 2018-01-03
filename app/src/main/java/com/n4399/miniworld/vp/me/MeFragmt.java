package com.n4399.miniworld.vp.me;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blueprint.Consistent;
import com.blueprint.LibApp;
import com.blueprint.basic.JBasePresenter;
import com.blueprint.helper.LogHelper;
import com.blueprint.helper.SpanHelper;
import com.blueprint.helper.StrHelper;
import com.blueprint.helper.UIhelper;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.AccountManage;
import com.n4399.miniworld.data.bean.AccountBean;
import com.n4399.miniworld.vp.basic.JBaseTitleFrgmt;
import com.n4399.miniworld.vp.me.mycoll.MyCollectAt;
import com.n4399.miniworld.vp.me.search.GuysSearchAt;
import com.n4399.miniworld.vp.me.setting.MeSettingAt;
import com.n4399.miniworld.vp.me.usercent.UserCenterActivity;
import com.n4399.miniworld.vp.me.usercent.share.UserShareListAt;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [我]
 */
public class MeFragmt extends JBaseTitleFrgmt implements MeContract.IMeView<AccountBean> {
    @BindView(R.id.me_user_icon) ImageView meUserIcon;
    @BindView(R.id.me_user_name) TextView meUserName;
    @BindView(R.id.me_login_mini_msg) TextView mLoginMsg_mini;
    private MePresenter mPresenter;
    private AccountBean mUser;

    @Override
    protected boolean requestNoTitleBar(){
        return true;
    }

    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout container){
        View rootview = inflater.inflate(R.layout.main_me_frgmt, container);
        ButterKnife.bind(this, rootview);
        mMultiStateLayout.showStateSucceed();
        mPresenter = new MePresenter(this);
    }

    private void toBindMini(){
        if(AccountManage.getSingleton().isLogin()) {
            mPresenter.toBindMini();
            //判断是否有绑定米你好
            //前往绑定米你好
        }

    }

    //点击头像进入 资料编辑 /没登录就登录
    private void login_detail(){
        //先 判断 是否有登陆
        if(AccountManage.getSingleton().isLogin()) {

        }else {
            mPresenter.toLogin();
        }
        mPresenter.checkLogin(getActivity());
    }

    @Override
    protected JBasePresenter initPresenter(){
        return new MePresenter(this);
    }

    @Override
    protected String setTitle(){
        return LibApp.findString(R.string.me_title);
    }

    @Override
    public void showLoading(){

    }

    @Override
    public void showError(@Consistent.ErrorCode int eCode){
        LogHelper.Log_e("获取不到用户信息  显示游客界面");
        showTourist();
    }

    @Override
    public void showSucceed(AccountBean data){
        //todo
        showTourist();
    }

    @Override
    public void showLogin(AccountBean user){
        mUser = user;
        AccountManage.getSingleton().updateAccount(user);
        //检查是否绑定米你号
        if(TextUtils.isEmpty(user.getMiniid())) {
            user.setMiniid(LibApp.findString(R.string.me_home_tobind_mini));
        }
        SpanHelper.getSpanModify(mLoginMsg_mini, "迷你号：", R.style.me_login_account_detail, R.style.me_login_account_desc,
                R.string.me_login_mini, user.getMiniid());
        //设置用户名
        meUserName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        meUserName.setBackground(new ColorDrawable(Color.TRANSPARENT));
        meUserName.setTextColor(Color.parseColor("#333333"));
        meUserName.setText(StrHelper.nullStrToEmpty(user.getNickname()));
        //        PicHelper.loadImage(,meUserIcon);
    }

    @Override
    public void showTourist(){
        //游客登陆
        UIhelper.RoundBgText(meUserName, LibApp.findColor(R.color.colorPrimary));
        meUserName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.me_user_name, R.id.me_to_setting, R.id.me_login_mini_msg, R.id.me_my_user_center, R.id.me_my_collection, R.id.me_my_share, R.id.me_my_find})
    public void onViewClicked(View view){
        switch(view.getId()) {
            case R.id.me_to_setting:
                MeSettingAt.start(getActivity());
                break;
            case R.id.me_login_mini_msg:
                toBindMini();
                break;
            case R.id.me_my_user_center:
                if(mUser != null) {
                    ViewCompat.setTransitionName(meUserIcon, Consistent.TransitionName.TRANS_AVATAR);
                    UserCenterActivity.start(getActivity(), mUser,meUserIcon); //进入 我的中心 查看 粉丝
                }else {
                    Toast.makeText(getContext(), "请先登录！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.me_my_collection:
                if(mUser != null) {
                    MyCollectAt.start(getActivity(), mUser);
                }else {
                    Toast.makeText(getContext(), "请先登录！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.me_my_share:
                if(mUser != null) {
                    UserShareListAt.start(getActivity(), mUser);
                }else {
                    Toast.makeText(getContext(), "请先登录！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.me_my_find:
                GuysSearchAt.start(getActivity());
                break;
            case R.id.me_user_name:
                login_detail();
                break;
        }
    }
}
