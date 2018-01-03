package com.n4399.miniworld.data.bean;

import android.widget.TextView;

import com.blueprint.Consistent;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;

import java.util.Random;

import static com.blueprint.helper.UIhelper.RoundBgTextRes;

/**
 * @another 江祖赟
 * @date 2017/7/5.
 */
public class MPackage implements IRecvData {

    /**
     * id : id
     * title : 材质包名称
     * pic : 图片地址
     * author : 作者昵称
     * desc : 简介
     * isLock : 是否有锁
     */

    private String id;
    private String title = "材质包";
    private String pic = Consistent.TEMP.PIC1;
    private String author = "小贝";
    private String desc = "这是一个很长很的描述这是一个很长很的描述这是一个很长很的描述这是一个很长很的描述这是一个很长很的描述这是一个很长很的描述这是一个很长很的描述这是一个很长很的描述这是一个很长很的描述";
    private boolean isLock = new Random().nextBoolean();

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

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public boolean isLock(){
        return isLock;
    }

    public void setLock(boolean lock){
        isLock = lock;
    }

    @Override
    public void bindHolder(RecyclerHolder holder){
        holder.setImageUrl(R.id.item_ws_material_iv_icon, pic).setText(R.id.item_ws_material_tv_actor, author)
                .setText(R.id.item_ws_material_tv_desc, desc).setText(R.id.item_ws_material_tv_title, title);
        if(isLock) {
            //解锁加入
            RoundBgTextRes((TextView)holder.getView(R.id.item_ws_material_tv_join),
                    R.color.item_live_top_ing, R.color.j_white);
            holder.setText(R.id.item_ws_material_tv_join, R.string.frgmt_ws_room_locked);
        }else {
            RoundBgTextRes((TextView)holder.getView(R.id.item_ws_material_tv_join), R.color.common_line,
                    R.color.j_white);
        }
    }
}
