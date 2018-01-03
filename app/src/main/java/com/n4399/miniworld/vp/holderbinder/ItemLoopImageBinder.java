package com.n4399.miniworld.vp.holderbinder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.widget.LoopImagePager;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.ItemLoopImage;
import com.blueprint.adapter.OnItemClickListener;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [轮播图]
 */
public class ItemLoopImageBinder extends ItemViewBinder<ItemLoopImage,RecyclerHolder> {

    OnItemClickListener mOnItemClickListener;

    public ItemLoopImageBinder(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    protected RecyclerHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent){
        return new RecyclerHolder(inflater.inflate(R.layout.recv_item_recom_loopimage, null));
    }

    @Override
    protected void onBindViewHolder(@NonNull final RecyclerHolder holder, @NonNull final ItemLoopImage item){
        LoopImagePager view = holder.getView(R.id.lip_recv_item_lipager);
        view.setPagerData(item.getImageUrls());
        view.setOnPagerItemClickListener(new LoopImagePager.onLoopImageClickListener() {
            @Override
            public void onItemClickd(int Position){
                mOnItemClickListener.onItemClicked(item.getCarouselBeanList().get(holder.getAdapterPosition()), getPosition(holder));
            }
        });
    }
}
