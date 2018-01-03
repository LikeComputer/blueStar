package com.n4399.miniworld.data.bean;

import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 江祖赟.
 * @date 2017/6/8
 * @des [轮播图 相关]
 */
public class ItemLoopImage implements IRecvData{
    private List<String> imageUrls;
    private List<CarouselBean> mCarouselBeanList;


    public ItemLoopImage(List<CarouselBean> carouselBeanList){
        mCarouselBeanList = carouselBeanList;
        imageUrls = new ArrayList<>();
        for(CarouselBean carouselBean : carouselBeanList) {
            imageUrls.add(carouselBean.getPic());
        }
    }

    public List<String> getImageUrls(){
        return imageUrls;
    }

    public List<CarouselBean> getCarouselBeanList(){
        return mCarouselBeanList;
    }

    public void setCarouselBeanList(List<CarouselBean> carouselBeanList){
        mCarouselBeanList = carouselBeanList;
        imageUrls = new ArrayList<>();
        for(CarouselBean carouselBean : carouselBeanList) {
            imageUrls.add(carouselBean.getPic());
        }
    }

    @Override
    public void bindHolder(RecyclerHolder holder){

    }
}
