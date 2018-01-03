package com.n4399.miniworld.data.bean;

import android.view.View;

import com.blueprint.Consistent;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;

import static com.n4399.miniworld.vp.live.LiveRecFrgmt.LIVE_ING;

/**
 * @another 江祖赟
 * @date 2017/6/27.
 * 视频
 */
public class LiveIngBean implements IRecvData{

    /**
     * id : id
     * title : 直播名称
     * pic : 直播截图
     * url : 链接地址
     * views : 观看人数
     * author : 主播昵称
     * authorPic : 主播头像地址
     */

    private String id;
    private String title = "标题";
    private String pic = Consistent.TEMP.ICON;
    private String url = Consistent.TEMP.URLT;
    private String views = "444";
    private String author = "钢筋";
    private int type;
    private String authorPic = Consistent.TEMP.AVATAR;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public LiveIngBean setTitle(String title){
        this.title = title;
        return this;
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

    public String getViews(){
        return views;
    }

    public void setViews(String views){
        this.views = views;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthorPic(){
        return authorPic;
    }

    public void setAuthorPic(String authorPic){
        this.authorPic = authorPic;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public void bindHolder(RecyclerHolder holder){
        if(LIVE_ING == type) {
            holder.setVisibility(R.id.item_live_type_rec, View.GONE);
        }else {
            holder.setVisibility(R.id.item_live_type_ing, View.GONE);
        }
        holder.setText(R.id.item_live_ing_anchor, author).setText(R.id.item_live_ing_onlines, views)
                .setText(R.id.item_live_rec_anchor, author).setText(R.id.item_live_rec_onlines, views)
                .setText(R.id.item_live_ing_title, title).setImageUrl(R.id.item_live_ing_pic, pic);
    }
}
