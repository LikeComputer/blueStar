//package com.n4399.miniworld.vp.holderbinder;
//
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextSwitcher;
//
//import com.blueprint.adapter.RecyclerHolder;
//import com.jakewharton.rxbinding2.view.RxView;
//import com.n4399.miniworld.R;
//import com.n4399.miniworld.data.bean.GuysBean;
//
//import java.util.concurrent.TimeUnit;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.functions.Consumer;
//import me.drakeet.multitype.ItemViewBinder;
//
///**
// * @another [https://github.com/ZuYun]
// * @desc [描述]
// */
//public class ItemGuysBinder extends ItemViewBinder<GuysBean,RecyclerHolder> {
//
//    OnGuysItemClickListener mClickListener;
//
//    public ItemGuysBinder(OnGuysItemClickListener clickListener){
//        mClickListener = clickListener;
//    }
//
//    @NonNull
//    @Override
//    protected RecyclerHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent){
//        return new RecyclerHolder(inflater.inflate(R.layout.item_me_guys, parent, false));
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull final RecyclerHolder holder, @NonNull final GuysBean item){
//        item.bindHolder(holder);
//        final TextSwitcher recvMeSfSw = holder.getView(R.id.recv_me_sf_sw);
//        RxView.clicks(recvMeSfSw).debounce(100, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(@io.reactivex.annotations.NonNull Object o) throws Exception{
//                        item.setFollow(!item.isFollow());
//                        recvMeSfSw.setText(item.getStateText());
//                        mClickListener.onFollowStar(item);//发请求 关注或取消关注
//                    }
//                });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v){
//                mClickListener.onItemClicked(item);
//            }
//        });
//
//    }
//}