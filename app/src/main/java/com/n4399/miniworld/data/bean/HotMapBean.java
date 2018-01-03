package com.n4399.miniworld.data.bean;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.blueprint.Consistent;
import com.blueprint.LibApp;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.helper.UIhelper;
import com.blueprint.widget.JDownloadProgView;
import com.n4399.miniworld.R;
import com.n4399.miniworld.helper.Data2UIhelper;
import com.n4399.miniworld.vp.jpublic.HopmapDownloadPresenter;
import com.n4399.miniworld.vp.workshop.mapdetail.MapDetailAt;

import java.util.Random;

import static com.blueprint.LibApp.findString;
import static com.blueprint.helper.DpHelper.dp2px;
import static com.n4399.miniworld.helper.Data2UIhelper.downloadProbutton;

/**
 * @another 江祖赟
 * @date 2017/7/7.
 * 地图
 */
public class HotMapBean implements IRecvData, View.OnClickListener, Parcelable, JDownloadProgView.OnDownloadProgressListener {
    /**
     * id : id
     * title : 标题
     * pic : 图片地址
     * url : 链接地址
     * typeName : 分类名称
     * author : 作者
     * down : 下载量
     * "size":"大小"
     */
    public static final int MODE_NORMAL = 0;
    public static final int MODE_SELECT = 1;
    private String id;
    private String title = "地图名称"+new Random().nextInt(10);
    private String pic = Consistent.TEMP.AVATAR;
    private String url;
    private String typeName = "类别";
    private String author = "小少";
    private int down = new Random().nextInt(99)+1;
    private float size = 2;
    boolean selected;
    private int mMode = MODE_NORMAL;
    private HopmapDownloadPresenter mDownloadPresenter;
    private float mInitProgress;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getPic(){
        return pic;
    }

    public void setPic(String pic){
        this.pic = pic;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getTypeName(){
        return typeName;
    }

    public void setTypeName(String typeName){
        this.typeName = typeName;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public int getDown(){
        return down;
    }

    public void setDown(int down){
        this.down = down;
    }

    public float getSize(){
        return size;
    }

    public void setSize(float size){
        this.size = size;
    }

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public void bindHolder(RecyclerHolder holder){
        //查询当前 url状态 下载中，未下载，已下载

        //处理 图片/ 中间文字
        configPublic(holder);

        if(holder.getView(R.id.item_wshop_recom_tv_action) instanceof JDownloadProgView) {
            //普通的下载 模式
            notmalProgress(holder);
        }else {
            //本地 地图管理 打开地图，选中删除 按钮切换
            forMapLocal(holder);
        }
        //右侧按钮点击事件
        holder.itemView.setTag(holder);
        holder.itemView.setOnClickListener(this);
    }

    private void forMapLocal(RecyclerHolder holder){
        final ViewSwitcher viewSwitcher = (ViewSwitcher)holder.getView(R.id.item_wshop_recom_tv_action);
        viewSwitcher.removeAllViews();
        viewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView(){
                View tempView = new CheckBox(viewSwitcher.getContext());
                if(MODE_NORMAL == mMode) {
                    //普通 模式
                    tempView = new JDownloadProgView(LibApp.getContext());
                    tempView.setLayoutParams(new ViewSwitcher.LayoutParams(( (int)dp2px(70) ), ( (int)dp2px(26) )));
                    downloadProbutton((JDownloadProgView)tempView, findString(R.string.item_wshop_recom_down_download),
                            findString(R.string.item_wshop_recom_down_open));
                    ( (JDownloadProgView)tempView ).setProgress(1);
                    ( (JDownloadProgView)tempView ).setOnDownloadProgressListener(HotMapBean.this);
                }else {
                    //勾选模式
                    FrameLayout.LayoutParams layoutParams = new ViewSwitcher.LayoutParams(( (int)dp2px(30) ),
                            ( (int)dp2px(30) ));
                    layoutParams.gravity = Gravity.RIGHT;
                    tempView.setLayoutParams(layoutParams);
                    ( (CheckBox)tempView ).setChecked(selected);
                    tempView.setOnClickListener(HotMapBean.this);
                }
                return tempView;
            }
        });
        viewSwitcher.setInAnimation(LibApp.getContext(), android.R.anim.slide_in_left);
        viewSwitcher.setOutAnimation(LibApp.getContext(), android.R.anim.slide_out_right);
        viewSwitcher.setTag(viewSwitcher.getCurrentView());//点击事件
        viewSwitcher.showNext();
    }

    private void notmalProgress(RecyclerHolder holder){
        JDownloadProgView jProgView = (JDownloadProgView)holder.getView(R.id.item_wshop_recom_tv_action);
        jProgView.setTag(jProgView);
        //todo 读取进度显示
        //1  0 zhijian
        downloadProbutton(jProgView, findString(R.string.item_wshop_recom_down_download),
                findString(R.string.item_wshop_recom_down_open));
        //todo
        jProgView.setProgress(Math.max(jProgView.getProgress(), down/100f));
        //view 是被复用的 bean则不是
        if(mDownloadPresenter == null) {
            System.out.println();
            mDownloadPresenter = new HopmapDownloadPresenter(this);
            jProgView.setOnDownloadProgressListener(this);
            mInitProgress = jProgView.getInitProgress();
        }else {
            jProgView.setInitProgress(mInitProgress);
        }
        //        holder.setOnClickListener(R.id.item_wshop_recom_tv_action, this);
    }

    private void configPublic(RecyclerHolder holder){
        //根据类型 设置label颜色
        int color = LibApp.findColor(R.color.colorAccent);
        UIhelper.RoundBgText((TextView)holder.getView(R.id.item_wshop_recom_change_tv_label), color);
        holder.setImageUrl(R.id.im_recv_common_icon, pic).setText(R.id.item_wshop_recom_change_tv_anthor, author)
                .setText(R.id.item_wshop_recom_change_tv_title, title)
                .setText(R.id.item_wshop_recom_change_tv_label, typeName)
                .setText(R.id.item_wshop_recom_change_tv_download, Data2UIhelper.downloadMsg(down, size));
    }

    public void setMode(int mode){
        mMode = mode;
    }

    @Override
    public void onClick(View v){
        if(v instanceof CheckBox) {
            CheckBox checkBox = (CheckBox)v;
            selected = checkBox.isChecked();
        }else {
            RecyclerHolder holder = (RecyclerHolder)v.getTag();
            if(MODE_SELECT == mMode) {
                CheckBox checkBox = (CheckBox)( (ViewSwitcher)holder.getView(R.id.item_wshop_recom_tv_action) )
                        .getCurrentView();
                checkBox.setChecked(selected = !selected);
            }else {
                //跳转到详情
                MapDetailAt.start((Activity)v.getContext(), this, holder);
            }
        }
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.pic);
        dest.writeString(this.url);
        dest.writeString(this.typeName);
        dest.writeString(this.author);
        dest.writeInt(this.down);
        dest.writeFloat(this.size);
        dest.writeByte(this.selected ? (byte)1 : (byte)0);
        dest.writeInt(this.mMode);
    }

    public HotMapBean(){
    }

    protected HotMapBean(Parcel in){
        this.id = in.readString();
        this.title = in.readString();
        this.pic = in.readString();
        this.url = in.readString();
        this.typeName = in.readString();
        this.author = in.readString();
        this.down = in.readInt();
        this.size = in.readFloat();
        this.selected = in.readByte() != 0;
        this.mMode = in.readInt();
    }

    public static final Parcelable.Creator<HotMapBean> CREATOR = new Parcelable.Creator<HotMapBean>() {
        @Override
        public HotMapBean createFromParcel(Parcel source){
            return new HotMapBean(source);
        }

        @Override
        public HotMapBean[] newArray(int size){
            return new HotMapBean[size];
        }
    };

    @Override
    public void onStart(JDownloadProgView jProgView){
        jProgView.setProgress(0.005f);
        mDownloadPresenter.onStart(jProgView);
        mInitProgress = jProgView.getInitProgress();
    }

    @Override
    public void onPause(JDownloadProgView jProgView, float progress){
        Toast.makeText(LibApp.getContext(), "暂停下载", Toast.LENGTH_SHORT).show();
        mDownloadPresenter.onPause(jProgView,progress);
        mInitProgress = jProgView.getInitProgress();
    }

    @Override
    public void onResume(JDownloadProgView jProgView, float progress){
        mDownloadPresenter.onResume(jProgView,progress);
        mInitProgress = jProgView.getInitProgress();
    }

    @Override
    public void onAfterDone(JDownloadProgView jProgView){
        //打开 游戏
        Toast.makeText(LibApp.getContext(), "打开游戏", Toast.LENGTH_SHORT).show();
    }
}