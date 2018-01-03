package com.n4399.miniworld.vp.workshop.online.seach;

import com.blueprint.basic.common.GeneralListContract;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.rx.RxUtill;
import com.n4399.miniworld.data.bean.RoomBean;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.blueprint.helper.LogHelper.Log_d;

public class RoomSearchResultPresenter extends GeneralListPresenter {

    public RoomSearchResultPresenter(GeneralListContract.View view){
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
                        List<RoomBean> roomBeanList = new ArrayList<>();
                        roomBeanList.add(new RoomBean());
                        roomBeanList.add(new RoomBean());
                        roomBeanList.add(new RoomBean());
                        roomBeanList.add(new RoomBean());
                        roomBeanList.add(new RoomBean());
                        roomBeanList.add(new RoomBean());
                        roomBeanList.add(new RoomBean());
                        mListView.showSucceed(roomBeanList);
                    }
                }));
    }
}