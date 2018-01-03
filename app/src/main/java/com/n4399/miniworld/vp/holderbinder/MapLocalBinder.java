package com.n4399.miniworld.vp.holderbinder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blueprint.adapter.BaseBinder;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.HotMapBean;

import me.drakeet.multitype.ItemViewBinder;

import static com.n4399.miniworld.data.bean.HotMapBean.MODE_NORMAL;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MapLocalBinder extends ItemViewBinder<HotMapBean,RecyclerHolder> {

    private int mMode = MODE_NORMAL;

    @NonNull
    @Override
    protected RecyclerHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent){
        return new RecyclerHolder(inflater.inflate(R.layout.item_ws_map_local, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerHolder holder, @NonNull HotMapBean item){
        item.setMode(mMode);
        item.bindHolder(holder);
    }

    public int getMode(){
        return mMode;
    }

    public void setMode(int mode){
        mMode = mode;
    }
}