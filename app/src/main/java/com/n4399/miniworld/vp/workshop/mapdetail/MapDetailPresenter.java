package com.n4399.miniworld.vp.workshop.mapdetail;

import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.rx.RxUtill;
import com.n4399.miniworld.data.bean.GuysBean;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.data.bean.MapDetailBean;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MapDetailPresenter extends GeneralListPresenter implements MapDetailContract.Presenter {

    MapDetailContract.View mView;

    public MapDetailPresenter(MapDetailContract.View view){
        super(view);
        mView = view;
    }

    @Override
    public void subscribe(Object from){
        collectDisposables(Flowable.just(1).doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(@NonNull Subscription subscription) throws Exception{
                mListView.showLoading();
            }
        }).delay(1, TimeUnit.SECONDS)
                .compose(RxUtill.defaultSchedulers_flow()).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception{
                        List<Object> hopmaplist = new ArrayList<>();
                        hopmaplist.add(new MapDetailBean(new GuysBean()));
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        hopmaplist.add(new HotMapBean());
                        mListView.showSucceed(hopmaplist);
                    }
                }));
    }
}