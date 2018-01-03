package com.n4399.miniworld.vp.me;

import android.app.Activity;

import com.blueprint.helper.CheckHelper;
import com.n4399.miniworld.data.AccountManage;
import com.n4399.miniworld.data.bean.AccountBean;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @another 江祖赟
 * @date 2017/6/15.
 */
public class MePresenter implements MeContract.IMePresenter {

    private final CompositeDisposable mCompositeDisposable;
    private MeContract.IMeView mView;

    public MePresenter(MeContract.IMeView view){
        mView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe(Object from){
        mView.showTourist();
    }

    @Override
    public void unsubscribe(){

    }

    @Override
    public void checkLogin(Activity activity){
        //判断是否有登录
        if(CheckHelper.checkObjects(AccountManage.getSingleton().getUserBean())) {
            mView.showLogin(new AccountBean());
        }else {
            //没登录
            //            mView.showLogin(new AccountBean());
            mView.showTourist();
        }
        // 注册接口一经调用，无论原先是否已经登录一律清除原有登录信息，重新进行注册并且登录步骤。

    }

    @Override
    public void featchUserInfo(){

    }

    @Override
    public void toBindMini(){

    }

    @Override
    public void toLogin(){
        mView.showLogin(new AccountBean());
    }
}
