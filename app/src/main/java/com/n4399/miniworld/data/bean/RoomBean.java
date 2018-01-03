package com.n4399.miniworld.data.bean;

import android.view.View;
import android.widget.TextView;

import com.blueprint.Consistent;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.helper.UIhelper;
import com.n4399.miniworld.R;

import java.util.Random;

import static com.blueprint.helper.SpanHelper.getFString;

/**
 * @another 江祖赟
 * @date 2017/7/5.
 */
public class RoomBean implements IRecvData {

    /**
     * id : id
     * title : 存档名称
     * pic : 图片地址
     * url : 链接地址
     * typeName : 分类名称
     * author : 作者昵称
     * miniid : 迷你号
     * ver : 版本号
     * isLock : 是否有锁
     * signal : 信号强度
     * onlineNum : 在线人数
     * totalNum : 最大容纳人数
     */

    private String id;
    private String title = "标题";
    private String pic = Consistent.TEMP.PIC1;
    private String url;
    private String typeName = "房间名字";
    private String author = "余屌丝";
    private String miniid = "19044";
    private String ver = "v1.4.5";
    private boolean isLock = new Random().nextBoolean();
    private String signal = "信号良好";
    private int totalNum = new Random().nextInt(6)+2;
    private int onlineNum = new Random().nextInt(totalNum)+1;

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

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getMiniid(){
        return miniid;
    }

    public void setMiniid(String miniid){
        this.miniid = miniid;
    }

    public String getVer(){
        return ver;
    }

    public void setVer(String ver){
        this.ver = ver;
    }

    public boolean isLock(){
        return isLock;
    }

    public void setLock(boolean lock){
        isLock = lock;
    }

    public String getSignal(){
        return signal;
    }

    public void setSignal(String signal){
        this.signal = signal;
    }

    public int getOnlineNum(){
        return onlineNum;
    }

    public void setOnlineNum(int onlineNum){
        this.onlineNum = onlineNum;
    }

    public int getTotalNum(){
        return totalNum;
    }

    public void setTotalNum(int totalNum){
        this.totalNum = totalNum;
    }

    @Override
    public void bindHolder(RecyclerHolder holder){
        holder.setImageUrl(R.id.item_ws_online_room_iv_icon, pic).setText(R.id.item_ws_online_room_tv_actor, author)
                .setText(R.id.item_ws_online_room_tv_title, title).setText(R.id.item_ws_online_room_tv_percent,
                getFString(R.string.item_ws_online_room_tv_percent, onlineNum, totalNum), getColor())
                .setText(R.id.item_ws_online_room_tv_signal, signal).setText(R.id.item_ws_online_room_tv_mapversion,
                getFString(R.string.item_ws_online_room_tv_mapversion, ver))
                .setText(R.id.item_ws_online_room_tv_mini, getFString(R.string.item_ws_online_room_tv_mini, miniid));
        if(onlineNum<totalNum) {
            if(isLock) {
                //解锁加入
                UIhelper.RoundBgTextRes((TextView)holder.getView(R.id.item_ws_online_room_tv_join),
                        R.color.item_live_top_ing, R.color.j_white);
                holder.setText(R.id.item_ws_online_room_tv_join, R.string.frgmt_ws_room_unlock_join);
                holder.setVisibility(R.id.item_ws_online_room_iv_lock, View.VISIBLE);
            }else {
                //一键加入
                UIhelper.RoundBgTextRes((TextView)holder.getView(R.id.item_ws_online_room_tv_join), R.color.colorAccent,
                        R.color.j_white);
                holder.setVisibility(R.id.item_ws_online_room_iv_lock, View.GONE);
            }
        }else {
            holder.setVisibility(R.id.item_ws_online_room_iv_lock, View.GONE);
            //房间已满
            holder.setText(R.id.item_ws_online_room_tv_join, R.string.frgmt_ws_room_overflow);
            UIhelper.RoundBgTextRes((TextView)holder.getView(R.id.item_ws_online_room_tv_join), R.color.common_line,
                    R.color.j_white);
        }

        UIhelper.RoundBgTextRes((TextView)holder.getView(R.id.item_ws_online_room_tv_label), R.color.colorAccent);
    }

    private int getColor(){
        if(onlineNum*1f/totalNum>0.9) {
            return R.color.red060;
        }
        return R.color.colorAccent;
    }
}
