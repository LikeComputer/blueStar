package jzy.easybind;

import android.app.Activity;
import android.os.Bundle;

import com.blueprint.LibApplication;

/**
 * @another 江祖赟
 * @date 2018/4/24.
 */
public class Japp extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onApplicationCreate() {

    }

    @Override
    protected boolean configDebugState() {
        return true;
    }

    @Override
    protected String[] configCreshWrapperHighlight() {
        return new String[0];
    }

    @Override
    protected void registerOnActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    protected void registerOnActivityDestroyed(Activity activity) {

    }

    @Override
    protected void registerOnTrimMemory(int level) {

    }

    @Override
    protected String configBaseUrl() {
        return null;
    }
}
