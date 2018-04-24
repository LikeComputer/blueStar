package com.yun.juimodel.data.commbean;

import android.view.View;
import com.google.gson.annotations.SerializedName;

/**
 * @another 江祖赟
 * @date 2017/12/21.
 * 轮播图
 */
public class CarouselBean extends IRoute {
    /**
     * id : id
     * title : 标题
     * pic : 图片地址
     * url : 链接地址,跳转
     */

    @SerializedName("id") public String id;
    @SerializedName("title") public String title;
    @SerializedName("tag") public String tag;


    @Override public int getBRid() {
        //return BR.loopImgItem;
        return 0;
    }


    @Override public int getLayoutRes() {
        //return R.layout.item_loopimg_corner_img;
        return 0;
    }


    @Override public void routeIntent(View view) {
        //RouterHelper.isLegalUrl(this);
    }
}
