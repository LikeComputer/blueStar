package com.n4399.miniworld.vp.holderbinder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.UnstableBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [插卡模块]
 */
public class ItemChangeMoudleBinder extends ItemViewBinder<UnstableBean,RecyclerHolder> {

    OnItemClickListener mListener;

    public ItemChangeMoudleBinder(OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    protected RecyclerHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent){
        return new RecyclerHolder(inflater.inflate(R.layout.recv_item_recom_changed, parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final RecyclerHolder holder, @NonNull final UnstableBean item){
        item.bindHolder(holder);
        //整个变动模块 的点击时间 没啥用
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mListener.onItemClicked(item, holder.getAdapterPosition());
            }
        });

        //插卡图片的点击事件
        holder.setOnClickListener(R.id.item_wshop_recom_iv_placeholder_fl, new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mListener.onItemClicked(item.getUrl(), holder.getAdapterPosition());
            }
        });
    }


}
