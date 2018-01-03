package com.n4399.miniworld.vp.workshop.topic.detail;

import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.HotMapBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @another [https://github.com/ZuYun]
 * @desc [专题详情]
 */
public class TopicDetailPresenter extends GeneralListPresenter implements TopicDetailContract.Presenter {

    TopicDetailContract.View mView;

    public TopicDetailPresenter(TopicDetailContract.View view){
        super(view);
        mView = view;
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
        mListView.showSucceed(mapBeanList);
    }
}