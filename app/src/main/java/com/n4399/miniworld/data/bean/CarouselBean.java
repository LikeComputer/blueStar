package com.n4399.miniworld.data.bean;

import com.blueprint.Consistent;

/**
 * @another 江祖赟
 * @date 2017/6/27.
 * {轮播图}
 */
public class CarouselBean {
    /**
     * id : id
     * title : 标题
     * pic : 图片地址
     * url : 链接
     */

    private String id;
    private String title = "轮播图";
    private String pic = Consistent.TEMP.ICON;
    private String url = Consistent.TEMP.URLT;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getPic(){
        return pic;
    }

    public CarouselBean setPic(String pic){
        this.pic = pic;
        return this;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }
}
