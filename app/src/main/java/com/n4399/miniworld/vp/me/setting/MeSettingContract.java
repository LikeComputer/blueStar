package com.n4399.miniworld.vp.me.setting;

import com.blueprint.basic.JBasePresenter;
import com.blueprint.basic.JBaseView;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public interface MeSettingContract {

    interface View<D> extends JBaseView<D> {
        void showUpdateMsg();
        void showCache(String cache);
    }

    interface Presenter<T> extends JBasePresenter<T> {
        void cheUpdate();
        void calcuteCache();
        void clearCache();
        void logOut();
    }
}