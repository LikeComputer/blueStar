package com.n4399.miniworld.vp.holderbinder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.GuysBean;
import com.n4399.miniworld.data.bean.RoomBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class ItemRoomBinder extends ItemViewBinder<RoomBean,RecyclerHolder> {

    OnGuysItemClickListener mClickListener;

    public ItemRoomBinder(OnGuysItemClickListener clickListener){
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    protected RecyclerHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent){
        return new RecyclerHolder(inflater.inflate(R.layout.item_me_guys, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final RecyclerHolder holder, @NonNull final RoomBean item){
        item.bindHolder(holder);
    }

    public interface OnGuysItemClickListener {
        void onFollowStar(GuysBean item);

        void onItemClicked(GuysBean item);
    }
}