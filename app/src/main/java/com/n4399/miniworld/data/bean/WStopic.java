package com.n4399.miniworld.data.bean;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.blueprint.Consistent;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.workshop.topic.detail.TopicDetailAt;

/**
 * @another 江祖赟
 * @date 2017/7/5.
 * 专题
 */
public class WStopic implements IRecvData, View.OnClickListener, Parcelable {

    /**
     * id : 专题id
     * title : 标题名称
     * pic : 专题展示图片地址
     * desc : 专题简介
     */

    private String id;
    private String title = "专题标题";
    private String pic = Consistent.TEMP.AVATAR;
    private String desc = "专题 描述";

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

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    @Override
    public void bindHolder(RecyclerHolder holder){
        holder.setImageUrl(R.id.item_ws_topic_iv_icon, pic).setText(R.id.item_ws_topic_tv_title, title)
                .setText(R.id.item_ws_topic_tv_desc, desc);
        holder.itemView.setTag(holder);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        TopicDetailAt.start((Activity)v.getContext(),this, (RecyclerHolder)v.getTag());
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.pic);
        dest.writeString(this.desc);
    }

    public WStopic(){
    }

    protected WStopic(Parcel in){
        this.id = in.readString();
        this.title = in.readString();
        this.pic = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<WStopic> CREATOR = new Parcelable.Creator<WStopic>() {
        @Override
        public WStopic createFromParcel(Parcel source){
            return new WStopic(source);
        }

        @Override
        public WStopic[] newArray(int size){
            return new WStopic[size];
        }
    };
}
