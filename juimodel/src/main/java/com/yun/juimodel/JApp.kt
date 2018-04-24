package com.yun.juimodel

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import com.blueprint.LibApplication
import com.blueprint.helper.StatusBarHelper
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * @another 江祖赟
 * @date 2018/1/16.
 */
class JApp : LibApplication() {
    override fun configBaseUrl(): String {
        return "666"
    }

    override fun onApplicationCreate() {
        Fresco.initialize(this)
    }

    override fun configCreshWrapperHighlight(): Array<String> {
        return arrayOf()
    }

    override fun registerOnActivityDestroyed(activity: Activity?) {
        println("===========")
    }

    override fun registerOnActivityStopped(activity: Activity?) {
        println("===========")
    }

    override fun registerOnActivityResumed(activity: Activity?) {
    }

    override fun registerOnActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        StatusBarHelper.StatusBarLightMode(activity)
    }

    override fun registerOnLowMemory() {
    }

    override fun registerOnConfigurationChanged(newConfig: Configuration?) {
    }

    override fun registerOnTrimMemory(level: Int) {
    }

    override fun onhandlerException(thread: Thread?, throwable: Throwable?): Boolean {
        println("===========")
        return false
    }

    override fun configDebugState(): Boolean {
        return BuildConfig.DEBUG
    }
}