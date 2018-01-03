package com.n4399.miniworld.vp.me;

import android.app.Activity;
import com.blueprint.basic.JBasePresenter;
import com.blueprint.basic.JBaseView;

/**
 * @another 江祖赟
 * @date 2017/6/15.
 */
public class MeContract {
    interface IMeView<T> extends JBaseView<T> {
        void showLogin(T user);
        void showTourist();
    }
    interface IMePresenter extends JBasePresenter {

        void checkLogin(Activity activity);

        void featchUserInfo();

        void toBindMini();

        void toLogin();
    }
}
