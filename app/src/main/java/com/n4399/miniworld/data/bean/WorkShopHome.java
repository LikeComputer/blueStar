package com.n4399.miniworld.data.bean;

import java.util.List;

/**
 * @another 江祖赟
 * @date 2017/7/5.
 */
public class WorkShopHome {

    /**
     * show : [{"id":"id","title":"标题","pic":"图片地址","url":"链接地址"}]
     * walfare : 福利中心描述语
     * hotlist : [{"id":"id","title":"标题","pic":"图片地址","url":"链接地址","typeName":"分类名称","author":"作者","down":"下载量","size":"大小"}]
     * module : [{"pic":"插卡图片地址","title":"模块名称","url":"插卡跳转地址","list":[{"id":"id","title":"标题","pic":"图片地址","url":"链接地址","typeName":"分类名称","author":"作者","down":"下载量","size":"大小"}]}]
     */

    private String walfare;
    private List<Show2Bean.ShowBean> show;
    private List<HotMapBean> hotlist;
    private List<UnstableBean> module;

    public String getWalfare(){
        return walfare;
    }

    public void setWalfare(String walfare){
        this.walfare = walfare;
    }

    public List<Show2Bean.ShowBean> getShow(){
        return show;
    }

    public void setShow(List<Show2Bean.ShowBean> show){
        this.show = show;
    }

    public List<HotMapBean> getHotlist(){
        return hotlist;
    }

    public void setHotlist(List<HotMapBean> hotlist){
        this.hotlist = hotlist;
    }

    public List<UnstableBean> getModule(){
        return module;
    }

    public void setModule(List<UnstableBean> module){
        this.module = module;
    }
}
