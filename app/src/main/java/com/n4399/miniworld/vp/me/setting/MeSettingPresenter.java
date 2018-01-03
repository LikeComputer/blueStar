package com.n4399.miniworld.vp.me.setting;

import com.blueprint.JSettingCenter;
import com.blueprint.basic.common.PublicPresenter;
import com.blueprint.helper.FileHelper;
import com.blueprint.rx.RxUtill;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MeSettingPresenter extends PublicPresenter implements MeSettingContract.Presenter {

    MeSettingContract.View mView;

    public MeSettingPresenter(MeSettingContract.View view){
        mView = view;
    }

    @Override
    public void subscribe(Object from){
        mView.showSucceed(null);
        calcuteCache();
        cheUpdate();
    }

    @Override
    public void unsubscribe(){
        clearDisposables();
    }

    @Override
    public void cheUpdate(){

    }

    @Override
    public void calcuteCache(){
        JSettingCenter.dirSizeObserver().compose(RxUtill.<Long>defaultSchedulers_single())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception{
                        mView.showCache(FileHelper.formatFileSize(aLong));
                    }
                });
    }

    @Override
    public void clearCache(){
        JSettingCenter.clearAppCache().compose(RxUtill.<Boolean>defaultSchedulers_single())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aLong) throws Exception{
                        mView.showCache(""+aLong);
                    }
                });
    }

    @Override
    public void logOut(){

    }
}