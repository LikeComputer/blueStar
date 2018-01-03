package com.n4399.miniworld.helper;

import com.blueprint.widget.JDownloadProgView;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @another 江祖赟
 * @date 2017/7/7.
 */
public class ProgressUpdateObserver<T> implements Observer<T> {
    private Disposable mD;
    private WeakReference<JDownloadProgView> mJProgViewWeakReference;

    public ProgressUpdateObserver(JDownloadProgView jProgView){
        mJProgViewWeakReference = new WeakReference<JDownloadProgView>(jProgView);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d){
        mD = d;
    }

    @Override
    public void onNext(@NonNull T aLong){
        if(mJProgViewWeakReference.get() != null) {
            mJProgViewWeakReference.get().setProgress(mJProgViewWeakReference.get().getProgress()+0.01f);
            if(mJProgViewWeakReference.get().getProgress() == 1) {
                mD.dispose();
            }
        }else {
            mD.dispose();
        }
    }

    @Override
    public void onError(@NonNull Throwable e){

    }

    @Override
    public void onComplete(){

    }

    public void dispose(){
        if(mD != null) {
            mD.dispose();
        }
    }
}
