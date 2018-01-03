package com.n4399.miniworld.data.bean;

import com.blueprint.Consistent;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [地图种子]
 */
public class MapseekBean implements IRecvData{

    /**
     * id : id
     * title : 标题
     * pic : 图片地址
     * url : 链接地址
     */

    private String id;
    private String title = "地图种子";
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

    public void setPic(String pic){
        this.pic = pic;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void bindHolder(RecyclerHolder holder){
        holder.setImageUrl(R.id.raiders_item_tvimg_icon, pic).setText(R.id.raiders_item_tvimg_title, title);
    }
}
