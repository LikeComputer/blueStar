package com.n4399.miniworld.data.bean;

import android.app.Activity;
import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.blueprint.Consistent;
import com.blueprint.LibApp;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.helper.SpanHelper;
import com.blueprint.helper.UIhelper;
import com.jakewharton.rxbinding2.view.RxView;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.me.usercent.UserCenterActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @another 江祖赟
 * @date 2017/6/30.
 * 重写 equal 当id/miniid有一个一样就认为是同一个用户
 * [粉丝 /关注]
 */
public class GuysBean extends AccountBean implements IRecvData, View.OnClickListener {

    /**
     * id : id
     * avatar : 用户头像地址
     * nick : 用户昵称
     * miniid : 用户迷你号
     * isFollow : 是否已关注
     * "isFans":"对方是否已关注自己(true)"
     */

    private boolean isFollow = new Random().nextBoolean();
    private boolean isFans = new Random().nextBoolean();

    public boolean isFollow(){
        return isFollow;
    }

    public void setFollow(boolean follow){
        isFollow = follow;
    }

    public boolean isFans(){
        return isFans;
    }

    public void setFans(boolean fans){
        isFans = fans;
    }

    public void bindHolder(RecyclerHolder holder){
        if(holder.getView(R.id.recv_me_sf_sw) instanceof TextSwitcher) {
            final TextSwitcher recvMeSfSw = holder.getView(R.id.recv_me_sf_sw);
            //            if(recvMeSfSw.getChildCount()>=2) {
            //                recvMeSfSw.removeAllViews();
            //            }
            if(recvMeSfSw.getChildCount() == 0) {
                recvMeSfSw.setFactory(new ViewSwitcher.ViewFactory() {
                    @Override
                    public View makeView(){
                        TextView textView = new TextView(LibApp.getContext());
                        int color = LibApp.findColor(R.color.colorAccent);
                        if(recvMeSfSw.getChildCount()<1) {
                            //正序
                            color = getTvColor(!isFollow);
                        }else {
                            color = getTvColor(isFollow);
                        }
                        UIhelper.RoundBgText(textView, color);
                        textView.setTextSize(14);//设置字体大小
                        textView.setGravity(Gravity.CENTER);
                        textView.setLayoutParams(new TextSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));//设置宽高属性
                        return textView;
                    }
                });
                recvMeSfSw.setInAnimation(LibApp.getContext(), R.anim.jswitcher_up_in);
                recvMeSfSw.setOutAnimation(LibApp.getContext(), R.anim.jswitcher_up_out);
            }
            recvMeSfSw.setCurrentText(getStateText());
            RxView.clicks(recvMeSfSw).throttleFirst(300, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
                @Override
                public void accept(@NonNull Object o) throws Exception{
                    isFollow = !isFollow;
                    recvMeSfSw.setText(getStateText());
                    //todo 发请求 关注或取消关注
                    Toast.makeText(LibApp.getContext(), ""+getStateText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        holder.setImageUrl(R.id.recv_me_sf_icon, avatar).setText(R.id.recv_me_sf_niki, nickname+isFollow)
                .setText(R.id.recv_me_sf_mini, SpanHelper.getFString(R.string.me_login_mini, miniid));
        holder.itemView.setOnClickListener(this);
    }

    private int getTvColor(boolean isFollow){
        if(isFollow) {
            //已关注
            return LibApp.findColor(R.color.colorAccent);
        }else {
            //未关注
            return LibApp.findColor(R.color.item_me_attention);
        }
    }

    public String getStateText(){
        if(!isFollow) {
            //未关注 显示 关注
            return LibApp.findString(R.string.me_item_tofollow);
        }else {
            //已关注 互相关注
            if(isFans) {
                return LibApp.findString(R.string.me_item_followed_each);
            }else {
                return LibApp.findString(R.string.me_item_followed);
            }
        }
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        super.writeToParcel(dest, flags);
        dest.writeByte(this.isFollow ? (byte)1 : (byte)0);
        dest.writeByte(this.isFans ? (byte)1 : (byte)0);
    }

    public GuysBean(){
    }

    protected GuysBean(Parcel in){
        super(in);
        this.isFollow = in.readByte() != 0;
        this.isFans = in.readByte() != 0;
    }

    public static final Creator<GuysBean> CREATOR = new Creator<GuysBean>() {
        @Override
        public GuysBean createFromParcel(Parcel source){
            return new GuysBean(source);
        }

        @Override
        public GuysBean[] newArray(int size){
            return new GuysBean[size];
        }
    };

    @Override
    public void onClick(View v){
        ImageView avatar = (ImageView)v.findViewById(R.id.recv_me_sf_icon);
        ViewCompat.setTransitionName(avatar, Consistent.TransitionName.TRANS_AVATAR);
        //进入个人详情
        UserCenterActivity.start((Activity)v.getContext(), this, avatar);
        //        }
    }
}
