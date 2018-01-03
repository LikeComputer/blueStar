package com.n4399.miniworld.data.bean;

import android.graphics.Color;
import android.widget.TextView;

import com.blueprint.Consistent;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.helper.UIhelper;
import com.blueprint.widget.JExpandableTextViews;
import com.n4399.miniworld.R;

import static com.blueprint.helper.SpanHelper.getFString;

/**
 * @another 江祖赟
 * @date 2017/7/10.
 */
public class MapDetailBean implements IRecvData {
    /**
     * id : id
     * title : 存档名称
     * pic : 图片地址
     * url : 下载链接
     * typeName : 分类名称
     * down : 下载量
     * size : 文件大小
     * label : 角标签,为空/精选/独家
     * desc : 简介
     * time :
     * authorInfo : {"uid":"uid","avatar":"用户头像地址","nickname":"用户昵称","miniid":"用户迷你号","isFollow":"是否已关注对方(true)","isFans":"对方是否已关注自己(true|false)"}
     */

    private String id;
    private String time;
    private GuysBean authorInfo;
    private String title = "详情标题";
    private String pic = Consistent.TEMP.PIC1;
    private String url;
    private String typeName = "生存";
    private int down = 10;
    private long size = 1024;
    private String label = "精选1";
    private String desc = "谁道闲情抛弃久？每到春来，惆怅还依旧。日日花前常病酒，不辞镜里朱颜瘦。\n河畔青芜堤上柳，为问新愁，何事年年有？独立小桥风满袖，平林新月人归后。";


    public MapDetailBean(){
    }

    public MapDetailBean(GuysBean authorInfo){
        this.authorInfo = authorInfo;
    }

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

    public String getTypeName(){
        return typeName;
    }

    public void setTypeName(String typeName){
        this.typeName = typeName;
    }

    public int getDown(){
        return down;
    }

    public void setDown(int down){
        this.down = down;
    }

    public long getSize(){
        return size;
    }

    public void setSize(long size){
        this.size = size;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public GuysBean getAuthorInfo(){
        return authorInfo;
    }

    public void setAuthorInfo(GuysBean authorInfo){
        this.authorInfo = authorInfo;
    }

    @Override
    public void bindHolder(RecyclerHolder holder){
        authorInfo.bindHolder(holder);
        //        Data2UIhelper.configMapLabelStyle(holder.getView(R.id
        // .item_ws_map_detail_type),
        // label);
        UIhelper.RoundBgTextRes(( (TextView)holder.getView(R.id.item_ws_map_detail_type) ), R.color.colorAccent);
        ( (JExpandableTextViews)holder.getView(R.id.item_ws_map_detail_publish_desc) ).setBackColor(Color.WHITE)
                .setText(desc);
        holder.setImageUrl(R.id.item_ws_map_detail_pic, pic).setText(R.id.item_ws_map_detail_publish_time, "2017/7/10")
                .setText(R.id.item_ws_map_detail_size, getFString(R.string.item_ws_map_detail_size, size))
                .setText(R.id.item_ws_map_detail_download, getFString(R.string.item_ws_map_detail_download, down))
                .setText(R.id.item_ws_map_detail_title, title).setText(R.id.item_ws_map_detail_type, typeName)
                .setText(R.id.item_ws_map_detail_label, label);
    }


}
