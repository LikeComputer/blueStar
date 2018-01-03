package com.n4399.miniworld.vp.workshop.online;

import com.blueprint.basic.common.GeneralListContract;
import com.n4399.miniworld.data.bean.RoomBean;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public interface OnlineContract {

    interface View extends GeneralListContract.View<RoomBean,RoomBean> {
        void showReSortedRoom();
    }

    interface Presenter extends GeneralListContract.Presenter {
        void roomReSort(int orderBy);

        void unlockedRoom(String id);

        void joinRoom(String id);
    }
}