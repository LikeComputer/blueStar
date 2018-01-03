package com.n4399.miniworld.vp.workshop.local;

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

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MapLocalPresenter extends GeneralListPresenter {

    public MapLocalPresenter(GeneralListContract.View view){
        super(view);
    }

    @Override
    public void subscribe(Object from){
        collectDisposables(Flowable.just(1).doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(@NonNull Subscription subscription) throws Exception{
                mListView.showLoading();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).delay(1, TimeUnit.SECONDS)
                .compose(RxUtill.defaultSchedulers_flow()).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception{
                        List<HotMapBean> hopmaplist = new ArrayList<>();
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