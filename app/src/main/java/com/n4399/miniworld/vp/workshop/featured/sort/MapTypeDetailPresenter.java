package com.n4399.miniworld.vp.workshop.featured.sort;

import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.HotMapBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MapTypeDetailPresenter extends GeneralListPresenter implements MapTypeDetailContract.Presenter {

    MapTypeDetailContract.View mView;

    public MapTypeDetailPresenter(MapTypeDetailContract.View view){
        super(view);
        mView = view;
    }

    @Override
    public void subscribe(Object from){
        List<HotMapBean> mapBeenlist = new ArrayList<>();
        mapBeenlist.add(new HotMapBean());
        mapBeenlist.add(new HotMapBean());
        mapBeenlist.add(new HotMapBean());
        mapBeenlist.add(new HotMapBean());
        mapBeenlist.add(new HotMapBean());
        mapBeenlist.add(new HotMapBean());
        mapBeenlist.add(new HotMapBean());
        mapBeenlist.add(new HotMapBean());
        mapBeenlist.add(new HotMapBean());
        mapBeenlist.add(new HotMapBean());
        mapBeenlist.add(new HotMapBean());
        mView.showSucceed(mapBeenlist);
    }
}