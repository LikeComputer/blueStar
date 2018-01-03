package com.n4399.miniworld.vp.workshop.online;

import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.RoomBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class OnlinePresenter extends GeneralListPresenter implements OnlineContract.Presenter {

    OnlineContract.View mView;

    public OnlinePresenter(OnlineContract.View view){
        super(view);
        mView = view;
    }

    @Override
    public void subscribe(Object from){
        List<RoomBean> roomBeanList = new ArrayList<>();
        roomBeanList.add(new RoomBean());
        roomBeanList.add(new RoomBean());
        roomBeanList.add(new RoomBean());
        roomBeanList.add(new RoomBean());
        roomBeanList.add(new RoomBean());
        roomBeanList.add(new RoomBean());
        roomBeanList.add(new RoomBean());
        roomBeanList.add(new RoomBean());
        roomBeanList.add(new RoomBean());
        roomBeanList.add(new RoomBean());
        roomBeanList.add(new RoomBean());

        mListView.showSucceed(roomBeanList);
    }

    @Override
    public void roomReSort(int orderBy){

    }

    @Override
    public void unlockedRoom(String id){

    }

    @Override
    public void joinRoom(String id){

    }
}