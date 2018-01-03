package com.n4399.miniworld.data.bean;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.blueprint.Consistent;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.helper.UIhelper;
import com.n4399.miniworld.R;

import static com.blueprint.Consistent.TEMP.JIANSHU;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [文字攻略/视频攻略/小编推荐 需要设置showlable]
 */
public class MsgCardBean {
    public static final String NEWS_TEXT = "1";
    public static final String RAIDERS_VIDEO = "2";
    public static final String GAME = "3";

    /**
     * id : id
     * title : 标题
     * pic : 图片地址
     * url : 链接地址
     * type :小编推荐【 1.新闻资讯 2.攻略心得 3.游戏教程】其他 【1，文字 2，视频】
     * desc : 描述
     * author : 作者
     * time : 发布时间
     */

    private String id;
    private String title = "标题";
    private String pic = Consistent.TEMP.PIC1;
    private String url = JIANSHU;
    private String type = "类型";
    private String desc = "描述";
    private String author = "江爷";
    private String time;
    private boolean showLabel;

    public MsgCardBean(String title){
        this.title = title;
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

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    public boolean isShowLabel(){
        return showLabel;
    }

    public void setShowLabel(boolean showLabel){
        this.showLabel = showLabel;
    }

    public void bindHolder(RecyclerHolder holder){
        holder.setText(R.id.recv_msgcard_tv_time, time).setText(R.id.recv_msgcard_tv_anthor, author)
                .setText(R.id.recv_msgcard_tv_desc, desc).setImageUrl(R.id.recv_msgcard_img, pic)
                .setText(R.id.recv_msgcard_tv_title, title);
        if(showLabel) {
            configLabelStyle(holder, this, R.id.recv_msgcard_img);
        }else {
            holder.setVisibility(R.id.recv_msgcard_tv_label, View.GONE);
        }
    }

    private void configLabelStyle(RecyclerHolder holder, MsgCardBean item, int id){
        int color = Color.RED;
        String label = "defaule";
        if(MsgCardBean.GAME.equals(item.getType())) {
            color = Color.BLACK;
            label = "游戏";
        }else if(MsgCardBean.NEWS_TEXT.equals(item.getType())) {
            color = Color.GREEN;
            label = "新闻";
        }else if(MsgCardBean.RAIDERS_VIDEO.equals(item.getType())) {
            color = Color.YELLOW;
            label = "攻略";
        }
        UIhelper.RoundBgText(( (TextView)holder.getView(id) ), color);
        ( (TextView)holder.getView(id) ).setText(label);
    }
}
