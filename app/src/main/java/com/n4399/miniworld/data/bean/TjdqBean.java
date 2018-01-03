package com.n4399.miniworld.data.bean;

import com.blueprint.Consistent;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [图鉴大全]
 */
public class TjdqBean implements IRecvData{

    /**
     * id : id
     * name : 名称
     * icon : 图片地址
     */

    private String id;
    private String name = "图鉴大全";
    private String icon = Consistent.TEMP.ICON;
    private String url = Consistent.TEMP.URLT;

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

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

    public void bindHolder(RecyclerHolder holder){
        holder.setImageUrl(R.id.raiders_item_tvimg_icon, icon).setText(R.id.raiders_item_tvimg_title, name);
    }
}
