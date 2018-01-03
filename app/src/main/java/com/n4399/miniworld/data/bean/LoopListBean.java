package com.n4399.miniworld.data.bean;

import java.util.List;

/**
 * @another 江祖赟
 * @date 2017/6/27.
 */
public class LoopListBean {
    private List<CarouselBean> carousel;
    private List<MsgCardBean> list;

    public List<CarouselBean> getCarousel(){
        return carousel;
    }

    public void setCarousel(List<CarouselBean> carousel){
        this.carousel = carousel;
    }

    public List<MsgCardBean> getList(){
        return list;
    }

    public void setList(List<MsgCardBean> list){
        this.list = list;
    }
}
