package com.n4399.miniworld.data.bean;

import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;

/**
 * @another 江祖赟
 * @date 2017/7/5.
 * 标题 分割
 */
public class FixIntervalBean implements IRecvData{
    private String title;

    public FixIntervalBean(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void bindHolder(RecyclerHolder holder){
        holder.setText(R.id.item_fixinterval_tv, title);
    }
}
