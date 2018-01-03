package com.n4399.miniworld.vp.workshop.featured;

import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.data.bean.WSfeature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FeaturePresenter extends GeneralListPresenter implements FeatureContract.Presenter {

    private FeatureContract.View mView;

    public FeaturePresenter(FeatureContract.View view){
        super(view);
        mView = view;
    }

    @Override
    public void subscribe(Object from){
        makeUpUIdata(null);
    }

    private void makeUpUIdata(WSfeature wSfeature){
//        mView.enAbleLoadMore(wSfeature.getHasNext());
//        List<WSfeature.TypelistBean> typelist = wSfeature.getTypelist();//分类
//        List<HotMapBean> mapBeenlist = wSfeature.getList();//精选
        ArrayList<WSfeature.TypelistBean> typelist = new ArrayList<>();
        typelist.add(new WSfeature.TypelistBean());
        typelist.add(new WSfeature.TypelistBean());
        typelist.add(new WSfeature.TypelistBean());
        typelist.add(new WSfeature.TypelistBean());
        typelist.add(new WSfeature.TypelistBean());
        typelist.add(new WSfeature.TypelistBean());
        typelist.add(new WSfeature.TypelistBean());
        typelist.add(new WSfeature.TypelistBean());
        mView.showSortGride(typelist);
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
        mView.showFeatureMapList(mapBeenlist);
        mView.showSucceed(null);
    }

    @Override
    public void ortsMapListBy(List<HotMapBean> orignList, int orderBy){
        Collections.sort(orignList, new Comparator<HotMapBean>() {
            @Override
            public int compare(HotMapBean o1, HotMapBean o2){
                return 0;
            }

        });
        mView.notifySortChange();
    }
}