package com.n4399.miniworld.data;

import com.n4399.miniworld.data.bean.AccountBean;

/**
 * @another 江祖赟
 * @date 2017/6/30.
 */
public class AccountManage {

    private AccountBean mUserBean;

    private AccountManage(){
        mUserBean = new AccountBean();
    }

    static class Inner {
        public static AccountManage sManage = new AccountManage();
    }

    public static AccountManage getSingleton(){
        return Inner.sManage;
    }

    public AccountManage updateAccount(AccountBean account){
        mUserBean = account;
        return this;
    }

    public AccountBean getUserBean(){
        return mUserBean;
    }

    //检查是否有登陆 /登陆是否有效
    public boolean isLogin(){
        return mUserBean != null;
    }

}
