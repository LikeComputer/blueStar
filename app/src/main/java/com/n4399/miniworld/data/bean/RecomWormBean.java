package com.n4399.miniworld.data.bean;

import android.graphics.drawable.Drawable;

import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.helper.DpHelper;
import com.blueprint.widget.JDownloadProgView;
import com.n4399.miniworld.R;

import static com.blueprint.LibApp.findColor;
import static com.blueprint.LibApp.findString;
import static com.blueprint.helper.SpanHelper.getFString;
import static com.n4399.miniworld.helper.Data2UIhelper.downloadProbutton;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [包括 活动/app更新/进入福利中心]
 */
public class RecomWormBean implements IRecvData{
    //提示 app更新
    public static final int TYPE_APP = 1;
    //首页 进入福利中心
    public static final int TYPE_WELFARE = 2;
    //福利详情 福利礼包
    public static final int TYPE_GIFTS = -1;
    /**
     * type -1 为福利中心
     * id : id
     * title : 礼包名称
     * icon : 图片地址
     * url : 链接地址
     * endtime : 截止日期
     */
    private String id;
    private String icon;
    private String title;
    private String url;
    private String endtime;

    private Drawable imageDrawable;
    private String recDesc;
    private MiniApp mMiniApp;
    private int type = TYPE_GIFTS;

    public MiniApp getMiniApp(){
        return mMiniApp;
    }

    public void setMiniApp(MiniApp miniApp){
        mMiniApp = miniApp;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getEndtime(){
        return endtime;
    }

    public void setEndtime(String endtime){
        this.endtime = endtime;
    }

    public String getIcon(){
        return icon;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getRecDesc(){
        return recDesc;
    }

    public void setRecDesc(String recDesc){
        this.recDesc = recDesc;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public Drawable getImageDrawable(){
        return imageDrawable;
    }

    public void setImageDrawable(Drawable imageDrawable){
        this.imageDrawable = imageDrawable;
    }

    public void bindHolder(RecyclerHolder holder){
        JDownloadProgView jProgView = holder.getView(R.id.item_wshop_recom_tv_action);
        if(TYPE_WELFARE == type) {
            //首页 福利中心
            downloadProbutton(jProgView, findString(R.string.recom_welfare_enter),
                    findString(R.string.recom_welfare_enter));
        }else if(TYPE_APP == type) {
            //检测 迷你世界更新
            String action = findString(R.string.item_wshop_recom_down_update);
            int actBgClolr = findColor(R.color.red060);
            if(mMiniApp.getAppState() == MiniApp.TO_DOWNLOAD) {
                actBgClolr = findColor(R.color.colorAccent);
                action = findString(R.string.item_wshop_recom_down_download);
            }
            jProgView.setRadio(DpHelper.dp2px(26)/2);
            jProgView.setTextSD(action, action);
            jProgView.setProgColors(actBgClolr, actBgClolr);
            holder.setText(R.id.item_wshop_recom_adv_name, mMiniApp.getAppName());
            recDesc = String
                    .format(findString(R.string.item_wshop_recom_mini_desc), mMiniApp.getVersion(), mMiniApp.getSize());
        }else {
            jProgView.setProgress(1);
            //福利详情
            downloadProbutton(jProgView, findString(R.string.recom_welfare_enter),
                    findString(R.string.item_wshop_recom_getwalfare));
            recDesc = getFString(R.string.item_wshop_recom_walfare_endtime, endtime);
            holder.setText(R.id.item_wshop_recom_adv_name, title);
        }
        holder.setText(R.id.item_wshop_recom_adv_desc, recDesc);
        if(imageDrawable != null) {
            //            holder.setImageBitmap(R.id.item_wshop_recom_icon, imageDrawable);
        }else if(icon != null) {
            holder.setImageUrl(R.id.item_wshop_recom_icon, icon);
        }
    }
}
