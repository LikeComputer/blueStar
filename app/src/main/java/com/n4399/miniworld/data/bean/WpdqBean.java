package com.n4399.miniworld.data.bean;

import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;

import static com.blueprint.Consistent.TEMP.ICON;
import static com.blueprint.Consistent.TEMP.URLT;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [物品百科]
 */
public class WpdqBean implements IRecvData{

    /**
     * id : id
     * name : 名称
     * icon : 图片地址
     * url : 链接详情
     */

    private String id;
    private String name = "物品百科";
    private String icon = ICON;
    private String url = URLT;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getIcon(){
        return icon;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void bindHolder(RecyclerHolder holder){
        holder.setImageUrl(R.id.raiders_item_tvimg_icon, icon).setText(R.id.raiders_item_tvimg_title, name);
    }
}
