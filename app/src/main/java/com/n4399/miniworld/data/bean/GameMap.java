package com.n4399.miniworld.data.bean;

import com.blueprint.Consistent;
import com.blueprint.adapter.RecyclerHolder;

/**
 * @another 江祖赟
 * @date 2017/6/30.
 */
public class GameMap {

    /**
     * id : id
     * title : 名称
     * pic : 图片地址
     * url : 链接地址
     * author : 作者
     * views : 下载量/访问量
     */

    private String id;
    private String title = "标题";
    private String pic = Consistent.TEMP.PIC1;
    private String url = Consistent.TEMP.BAIDU;
    private String author = "杨师弟";
    private String views;

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

    public void setPic(String pic){
        this.pic = pic;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getViews(){
        return views;
    }

    public void setViews(String views){
        this.views = views;
    }

    public void bindHolder(RecyclerHolder holder){

    }
}
