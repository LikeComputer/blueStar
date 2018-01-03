package com.n4399.miniworld.data.bean;

import android.graphics.drawable.Drawable;

/**
 * @another 江祖赟
 * @date 2017/7/4.
 */
public class MiniApp {
    public static final int TO_DOWNLOAD = 0;
    public static final int TO_UPDATE = 1;
    public static final int TO_PLAY = 2;

    private String appName;
    private String version;
    private float size;
    private Drawable icon;
    private String channel;
    private int appState = TO_DOWNLOAD;
    private String downloadUrl;

    public String getDownloadUrl(){
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl){
        this.downloadUrl = downloadUrl;
    }

    public String getVersion(){
        return version;
    }

    public void setVersion(String version){
        this.version = version;
    }

    public float getSize(){
        return size;
    }

    public void setSize(float size){
        this.size = size;
    }

    public Drawable getIcon(){
        return icon;
    }

    public void setIcon(Drawable icon){
        this.icon = icon;
    }

    public String getAppName(){
        return appName;
    }

    public void setAppName(String appName){
        this.appName = appName;
    }

    public String getChannel(){
        return channel;
    }

    public void setChannel(String channel){
        this.channel = channel;
    }

    public int getAppState(){
        return appState;
    }

    public void setAppState(int appState){
        this.appState = appState;
    }
}
