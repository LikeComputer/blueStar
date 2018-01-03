package com.n4399.miniworld.vp.holderbinder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.Show2Bean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class ImagesBinder extends ItemViewBinder<Show2Bean,RecyclerHolder> {

    private OnItemClickListener<Object> mItemClickListener;

    public ImagesBinder(OnItemClickListener<Object> itemClickListener){
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    protected RecyclerHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent){
        return new RecyclerHolder(inflater.inflate(R.layout.item_wshop_images_binder, parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final RecyclerHolder holder, @NonNull final Show2Bean item){
        holder.setImageUrl(R.id.item_wshop_recom_image2_1, item.getShow().get(0).getPic())
                .setImageUrl(R.id.item_wshop_recom_image2_2, item.getShow().get(1).getPic())
                .setOnClickListener(R.id.item_wshop_recom_image2_1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        mItemClickListener.onItemClicked(item.getShow().get(0), 0);
                    }
                }).setOnClickListener(R.id.item_wshop_recom_image2_2, new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mItemClickListener.onItemClicked(item.getShow().get(1), 1);
            }
        });
    }
}