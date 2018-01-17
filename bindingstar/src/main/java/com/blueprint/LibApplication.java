package com.blueprint;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import com.blueprint.crash.CrashWrapper;
import com.blueprint.crash.UncaughtExceptionInterceptor;
import me.tatarka.bindingcollectionadapter2.Utils;

/**
 * @another 江祖赟
 * @date 2018/1/16.
 */
public abstract class LibApplication extends Application implements UncaughtExceptionInterceptor {

    protected CrashWrapper mCrashWrapper;


    @Override public void onCreate() {
        super.onCreate();
        mCrashWrapper = LibApp.takeCare(this, configDebugState());
        LibApp.setBaseUrl(configBaseUrl());
        Utils.care(this);
        mCrashWrapper.withKeys(configCreshWrapperHighlight()).setAutoSaveLog(true).watching(this);
        //=============================
        LibApp.registerLibApplicationStateCallbacks(new LibApp.LibApplicationStateCallbacks() {
            @Override void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                super.onActivityCreated(activity, savedInstanceState);
                registerOnActivityCreated(activity, savedInstanceState);
            }


            @Override void onActivityResumed(Activity activity) {
                super.onActivityResumed(activity);
                registerOnActivityResumed(activity);
            }


            @Override void onActivityStopped(Activity activity) {
                super.onActivityStopped(activity);
                registerOnActivityStopped(activity);
            }


            @Override void onActivityDestroyed(Activity activity) {
                super.onActivityDestroyed(activity);
                registerOnActivityDestroyed(activity);
            }


            @Override void onTrimMemory(int level) {
                super.onTrimMemory(level);
                registerOnTrimMemory(level);
            }


            @Override void onConfigurationChanged(Configuration newConfig) {
                super.onConfigurationChanged(newConfig);
                registerOnConfigurationChanged(newConfig);
            }


            @Override void onLowMemory() {
                super.onLowMemory();
                registerOnLowMemory();
            }
        });

        onApplicationCreate();
    }


    protected abstract void onApplicationCreate();

    /**
     * 配置是否处于debug环境
     */
    protected abstract boolean configDebugState();

    /**
     * debug版本时 注册app奔溃的时候 弹出奔溃堆栈页面中 需要高亮的关键字
     */
    protected abstract String[] configCreshWrapperHighlight();

    protected abstract void registerOnActivityCreated(Activity activity, Bundle savedInstanceState);

    protected void registerOnActivityResumed(Activity activity){}

    protected void registerOnActivityStopped(Activity activity){}

    protected abstract void registerOnActivityDestroyed(Activity activity);

    protected abstract void registerOnTrimMemory(int level);

    protected void registerOnLowMemory(){}

    protected void registerOnConfigurationChanged(Configuration newConfig){}

    public boolean onhandlerException(Thread thread, Throwable throwable){
        return false;
    }


    protected String configBaseUrl() {
        return null;
    }


    public static String getCurrentChannel() {
        String channel = "";
        try {
            ApplicationInfo appInfo = LibApp.getContext()
                                            .getPackageManager()
                                            .getApplicationInfo(LibApp.getContext().getPackageName(),
                                                    PackageManager.GET_META_DATA);
            channel = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            channel = "非法";
        }
        return channel;
    }
}
