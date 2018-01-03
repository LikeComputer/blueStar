package com.n4399.miniworld.vp.jpublic;

import android.widget.Toast;

import com.blueprint.LibApp;
import com.blueprint.widget.JDownloadProgView;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.helper.ProgressUpdateObserver;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * @author yun.
 * @date 2017/7/9
 * @des [一句话描述]
 * @since [https://github.com/mychoices]
 * <p><a href="https://github.com/mychoices">github</a>
 */
public class HopmapDownloadPresenter {

    HotMapBean mHotMapBean;

    private ProgressUpdateObserver<Object> mLongProgressUpdateObserver;

    public HopmapDownloadPresenter(HotMapBean hotMapBean){
        mHotMapBean = hotMapBean;
    }

    public void onStart(JDownloadProgView jProgView){
        jProgView.setProgress(0.005f);
        mLongProgressUpdateObserver = new ProgressUpdateObserver<>(jProgView);
        Observable.interval(500, TimeUnit.MILLISECONDS).subscribe(mLongProgressUpdateObserver);
    }

    public void onPause(JDownloadProgView jProgView, float progress){
        Toast.makeText(LibApp.getContext(), "暂停下载", Toast.LENGTH_SHORT).show();
        mLongProgressUpdateObserver.dispose();
    }

    public void onResume(JDownloadProgView jProgView, float progress){
        mLongProgressUpdateObserver = new ProgressUpdateObserver<>(jProgView);
        Observable.interval(500, TimeUnit.MILLISECONDS).subscribe(mLongProgressUpdateObserver);
    }
}

