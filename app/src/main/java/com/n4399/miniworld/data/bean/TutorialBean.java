package com.n4399.miniworld.data.bean;

import com.blueprint.Consistent;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;

import java.util.Random;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [游戏教程]
 */
public class TutorialBean implements IRecvData{

    /**
     * type : 后台设置后生成的类型id
     * title : 合集名称
     * pic : 图片地址
     */

    private String type;
    private String title = "游戏教程:"+new Random().nextInt(10);
    private String pic = Consistent.TEMP.ICON;

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
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

    public void bindHolder(RecyclerHolder holder){
        holder.setImageUrl(R.id.item_raiders_tutorial_icon, pic).setText(R.id.item_raiders_tutorial_tv_title, title);
    }
}
