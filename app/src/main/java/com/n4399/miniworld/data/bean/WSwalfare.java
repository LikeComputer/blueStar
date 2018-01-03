package com.n4399.miniworld.data.bean;

import com.blueprint.Consistent;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;

import java.util.List;

import static com.blueprint.helper.SpanHelper.getFString;

/**
 * @another 江祖赟
 * @date 2017/7/5.
 */
public class WSwalfare {

    private List<CarouselBean> carousel;
    private List<RecomWormBean> gifts;
    private List<ActivitysBean> activitys;

    public List<CarouselBean> getCarousel(){
        return carousel;
    }

    public void setCarousel(List<CarouselBean> carousel){
        this.carousel = carousel;
    }

    public List<RecomWormBean> getGifts(){
        return gifts;
    }

    public void setGifts(List<RecomWormBean> gifts){
        this.gifts = gifts;
    }

    public List<ActivitysBean> getActivitys(){
        return activitys;
    }

    public void setActivitys(List<ActivitysBean> activitys){
        this.activitys = activitys;
    }

    public static class ActivitysBean  implements IRecvData {
        /**
         * id : id
         * title : 活动名称
         * pic : 活动展示图片地址
         * status : 预热，进行中，已过期
         * desc : 活动简介
         * begintime : 活动开始日期
         * endtime : 活动截止日期
         * url : 活动地址
         */

        private String id;
        private String title = "福利详情 活动";
        private String pic = Consistent.TEMP.PIC1;
        private String status;
        private String desc = "福利详情 描述";
        private String begintime;
        private String endtime;
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

        public String getStatus(){
            return status;
        }

        public void setStatus(String status){
            this.status = status;
        }

        public String getDesc(){
            return desc;
        }

        public void setDesc(String desc){
            this.desc = desc;
        }

        public String getBegintime(){
            return begintime;
        }

        public void setBegintime(String begintime){
            this.begintime = begintime;
        }

        public String getEndtime(){
            return endtime;
        }

        public void setEndtime(String endtime){
            this.endtime = endtime;
        }

        public String getUrl(){
            return url;
        }

        public void setUrl(String url){
            this.url = url;
        }

        public void bindHolder(RecyclerHolder holder){
            holder.setImageUrl(R.id.item_walfare_activitys_pic, pic).setText(R.id.item_walfare_activitys_title, title)
                    .setText(R.id.item_walfare_activitys_status, status)
                    .setText(R.id.item_walfare_activitys_desc, getFString(R.string.item_walfare_activitys_desc, desc))
                    .setText(R.id.item_walfare_activitys_duration,
                            getFString(R.string.item_walfare_activitys_duration, begintime+"-"+endtime));
        }
    }
}
