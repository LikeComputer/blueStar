package com.yun.juimodel.data.commbean;

import android.view.View;
import com.blueprint.helper.ToastHelper;
import com.google.gson.annotations.SerializedName;
import com.yun.helper.BindingReusePagerAdapter;
import com.yun.juimodel.R;

/**
 * @another 江祖赟
 * @date 2017/8/29 0029.
 * 跳转协议 轮播图的跳转协议，当然还有不带图片的跳转协议<br>
 * 包括 跳转协议和 图片地址
 */
public class IRoute implements BindingReusePagerAdapter.JVpItem{
    @SerializedName("url") public String url;
    @SerializedName("pic") public String pic;


    public IRoute() {
    }


    public IRoute(String url, String pic) {
        this.url = url;
        this.pic = pic;
    }


    public IRoute(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * 轮播图 协议跳转
     * @param view
     */
    public void routeIntent(View view) {
        ToastHelper.showShort("复写 跳转协议");
    }

    @Override public int getBRid() {
        return com.yun.juimodel.BR.loopImgItem;
    }


    @Override public int getLayoutRes() {
        return R.layout.item_loopimg_corner_img;
    }
}
