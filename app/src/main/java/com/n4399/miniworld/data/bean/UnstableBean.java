package com.n4399.miniworld.data.bean;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewSwitcher;

import com.blueprint.LibApp;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;

import java.util.List;
import java.util.Random;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [一句话描述]
 */
public class UnstableBean {

    /**
     * pic : 插卡图片地址
     * title : 模块名称
     * "url":"插卡跳转地址",
     * list : [{"id":"id","title":"标题","pic":"图片地址","url":"链接地址","typeName":"分类名称","author":"作者","down":"下载量"}]
     */

    private String pic;
    private String title;
    private String url;
    private List<HotMapBean> list;

    public String getPic(){
        return pic;
    }

    public void setPic(String pic){
        this.pic = pic;
    }

    public String getTitle(){
        return title;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public List<HotMapBean> getList(){
        return list;
    }

    public void setList(List<HotMapBean> list){
        this.list = list;
    }

    public void bindHolder(RecyclerHolder holder){
        if(pic != null) {
            //插卡图片
            holder.setImageUrl(R.id.item_wshop_recom_iv_placeholder, pic);
        }else {
            //普通地图
            holder.setVisibility(R.id.item_wshop_recom_iv_placeholder_fl, View.GONE);
        }
        //固定四个地图
        final ViewSwitcher viewSwitcher1 = holder.getView(R.id.item_wshop_recom_hotitem_1);
        final ViewSwitcher viewSwitcher2 = holder.getView(R.id.item_wshop_recom_hotitem_2);
        final ViewSwitcher viewSwitcher3 = holder.getView(R.id.item_wshop_recom_hotitem_3);
        final ViewSwitcher viewSwitcher4 = holder.getView(R.id.item_wshop_recom_hotitem_4);

        initSwitcher(viewSwitcher1, viewSwitcher2, viewSwitcher3, viewSwitcher4);
        //动态模块 标题 换一批 左边
        holder.setText(R.id.tv_recv_recom_change_title, title)
                .setOnClickListener(R.id.btn_recv_recom_change_next, new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        sWitcherShowNext(viewSwitcher1);
                        sWitcherShowNext(viewSwitcher2);
                        sWitcherShowNext(viewSwitcher3);
                        sWitcherShowNext(viewSwitcher4);
                    }
                });
    }

    private void initSwitcher(ViewSwitcher viewSwitcher1, ViewSwitcher viewSwitcher2, ViewSwitcher viewSwitcher3, ViewSwitcher viewSwitcher4){
        viewSwitcher1.setTag(viewSwitcher1.getTag() == null ? 0 : viewSwitcher1.getTag());
        viewSwitcher2.setTag(viewSwitcher2.getTag() == null ? 1 : viewSwitcher2.getTag());
        viewSwitcher3.setTag(viewSwitcher3.getTag() == null ? 2 : viewSwitcher3.getTag());
        viewSwitcher4.setTag(viewSwitcher4.getTag() == null ? 3 : viewSwitcher3.getTag());
        configViewSwitch(viewSwitcher1, (Integer)viewSwitcher1.getTag());
        configViewSwitch(viewSwitcher2, (Integer)viewSwitcher2.getTag());
        configViewSwitch(viewSwitcher3, (Integer)viewSwitcher3.getTag());
        configViewSwitch(viewSwitcher4, (Integer)viewSwitcher4.getTag());
    }

    private void sWitcherShowNext(ViewSwitcher switcher){
        int index = (int)switcher.getTag();
        index = index+4>=list.size() ? ( index+4 )%4 : ( index+4 );//防止超出
        bindHotItem(new RecyclerHolder(switcher.getNextView()), list.get(index));
        switcher.setTag(index);
        switcher.showNext();
    }

    private void configViewSwitch(final ViewSwitcher viewSwitcher, final int position){
        if(viewSwitcher.getChildCount() == 0) {
            viewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                @Override
                public View makeView(){
                    return LayoutInflater.from(viewSwitcher.getContext()).inflate(R.layout.item_ws_map, null);
                }
            });
            if(new Random().nextInt(2) == 1) {
                viewSwitcher.setInAnimation(LibApp.getContext(), android.R.anim.slide_in_left);
                viewSwitcher.setOutAnimation(LibApp.getContext(), android.R.anim.slide_out_right);
            }else {
                viewSwitcher.setInAnimation(LibApp.getContext(), R.anim.jswitcher_up_in);
                viewSwitcher.setOutAnimation(LibApp.getContext(), R.anim.jswitcher_up_out);
            }
        }
        bindHotItem(new RecyclerHolder(viewSwitcher.getCurrentView()), list.get(position));
    }

    public void bindHotItem(RecyclerHolder holder, final HotMapBean bean){
        bean.bindHolder(holder);
        //        //查询当前 url状态 下载中，未下载，已下载
        //        //根据类型 设置label颜色
    }
}
