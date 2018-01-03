package com.n4399.miniworld.vp.me.usercent.share;

import com.blueprint.basic.common.GeneralListContract;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.HotMapBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class UserSharePresenter extends GeneralListPresenter {

    public UserSharePresenter(GeneralListContract.View view){
        super(view);
    }

    @Override
    public void subscribe(Object from){

        List<HotMapBean> mapBeanList = new ArrayList<>();
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mapBeanList.add(new HotMapBean());
        mListView.showSucceed(mapBeanList);
    }

    @Override
    public void unsubscribe(){
        clearDisposables();
    }
}