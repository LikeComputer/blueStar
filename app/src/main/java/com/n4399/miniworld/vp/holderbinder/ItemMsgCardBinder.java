package com.n4399.miniworld.vp.holderbinder;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.helper.LogHelper;
import com.blueprint.helper.SpanHelper;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.MsgCardBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [小编推荐，文字视频攻略]
 */
public class ItemMsgCardBinder extends ItemViewBinder<MsgCardBean,RecyclerHolder> {

    private MsgCardItemListener mListener;
    private String mSearchKey;

    public ItemMsgCardBinder(MsgCardItemListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    protected RecyclerHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent){
        return new RecyclerHolder(inflater.inflate(R.layout.recv_raider_recom_msg_card, parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final RecyclerHolder holder, @NonNull final MsgCardBean item){

//todo        item.bindHolder(holder);

        if(!TextUtils.isEmpty(mSearchKey)) {
            LogHelper.Log_d("---关键字--"+mSearchKey);
            SpanHelper.getSpanModify(holder.getView(R.id.recv_msgcard_tv_title), mSearchKey, item.getTitle(),
                    R.style.search_key_style, R.style.search_normal_style);
        }else {
            holder.setText(R.id.recv_msgcard_tv_title, item.getTitle());
        }

        holder.setText(R.id.recv_msgcard_tv_label, item.getType()).setText(R.id.recv_msgcard_tv_time, "时间")
                .setText(R.id.recv_msgcard_tv_anthor, "作者").setText(R.id.recv_msgcard_tv_desc, "描述信息")
                .setImageUrl(R.id.recv_msgcard_img, item.getPic());
        holder.setOnClickListener(R.id.recv_msgcard_, new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mListener.onMsgCardItemClicked(item, getPosition(holder));
            }
        });
    }

    public interface MsgCardItemListener {

        void onMsgCardItemClicked(MsgCardBean item, int position);
    }

    public void searchKey(String searchKey){

        mSearchKey = searchKey;
    }
}
