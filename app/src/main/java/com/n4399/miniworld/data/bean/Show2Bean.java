package com.n4399.miniworld.data.bean;

import com.blueprint.Consistent;

import java.util.List;

/**
 * @another 江祖赟
 * @date 2017/7/4.
 */
public class Show2Bean {


    private List<ShowBean> show;

    public List<ShowBean> getShow(){
        return show;
    }

    public void setShow(List<ShowBean> show){
        this.show = show;
    }

    public static class ShowBean {
        /**
         * id : id
         * title : 标题
         * pic : 图片地址
         * url : 链接地址
         */

        private String id;
        private String title;
        private String pic = Consistent.TEMP.PIC1;
        private String url;

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
    }
}
