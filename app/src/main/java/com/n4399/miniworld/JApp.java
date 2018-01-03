package com.n4399.miniworld;

import android.app.Application;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.blueprint.LibApp;
import com.blueprint.helper.PicHelper;
import com.blueprint.loadimage.IImgEngineProvider;
import com.blueprint.loadimage.ImgShowConfig;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

public class JApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        LibApp.fly(this,BuildConfig.DEBUG);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
//        LeakCanary.install(this);

        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        PicHelper.init(new IImgEngineProvider() {
            @Override
            public void loadNormal(Context ctx, ImgShowConfig config){

            }

            @Override
            public void loadNormal(ImageView iv, String url){
                Glide.with(iv.getContext()).load(url).error(R.drawable.default_sculpture_80)
                        .placeholder(R.drawable.default_sculpture_80).into(iv);
            }

            @Override
            public void loadNormal(ImageView iv, String url, int width, int height){
                Glide.with(iv.getContext()).load(url).error(R.drawable.default_sculpture_80)
                        .placeholder(R.drawable.default_sculpture_80).override(width, height).into(iv);
            }

            @Override
            public void loadNormal2(ImageView iv, String url, @DrawableRes int reserr, @DrawableRes int resloading){
                Glide.with(iv.getContext()).load(url).error(reserr)
                        .placeholder(resloading).into(iv);
            }

            @Override
            public void loadCache(Context ctx, ImgShowConfig config){

            }
        });

    }

    public static Context getInstance(){
        return LibApp.getContext();
    }

}
