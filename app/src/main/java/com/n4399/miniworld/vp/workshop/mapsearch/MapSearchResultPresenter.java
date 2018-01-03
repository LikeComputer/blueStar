package com.n4399.miniworld.vp.workshop.mapsearch;

import com.blueprint.basic.common.GeneralListContract;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.rx.RxUtill;
import com.n4399.miniworld.data.bean.HotMapBean;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.blueprint.helper.LogHelper.Log_d;

public class MapSearchResultPresenter extends GeneralListPresenter {

    public MapSearchResultPresenter(GeneralListContract.View view){
        super(view);
    }

    @Override
    public void search(String key){
        Log_d("搜索关键字："+key+"--------"+mListView.toString());
        collectDisposables(Flowable.just(1).doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(@NonNull Subscription subscription) throws Exception{
                mListView.showLoading();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).delay(1, TimeUnit.SECONDS)
                .compose(RxUtill.defaultSchedulers_flow()).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception{
                        List<HotMapBean> hotMapBeenlist = new ArrayList<HotMapBean>();
                        hotMapBeenlist.add(new HotMapBean());
                        hotMapBeenlist.add(new HotMapBean());
                        hotMapBeenlist.add(new HotMapBean());
                        hotMapBeenlist.add(new HotMapBean());
                        hotMapBeenlist.add(new HotMapBean());
                        hotMapBeenlist.add(new HotMapBean());
                        hotMapBeenlist.add(new HotMapBean());
                        hotMapBeenlist.add(new HotMapBean());
                        hotMapBeenlist.add(new HotMapBean());
                        mListView.showSucceed(hotMapBeenlist);
                    }
                }));
    }
}