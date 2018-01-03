package com.n4399.miniworld.vp.workshop.recommend.walfare;

import com.blueprint.LibApp;
import com.blueprint.basic.common.GeneralListContract;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.CarouselBean;
import com.n4399.miniworld.data.bean.FixIntervalBean;
import com.n4399.miniworld.data.bean.ItemLoopImage;
import com.n4399.miniworld.data.bean.RecomWormBean;
import com.n4399.miniworld.data.bean.WSwalfare;

import java.util.ArrayList;
import java.util.List;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class WalfarePresenter extends GeneralListPresenter {

    public WalfarePresenter(GeneralListContract.View view){
        super(view);
    }

    @Override
    public void subscribe(Object from){
        List succeed = new ArrayList();
        WSwalfare wSwalfare = new WSwalfare();
        //轮播图
        List<String> urls = new ArrayList<>();
        urls.add("https://ws1.sinaimg.cn/large/610dc034ly1fgllsthvu1j20u011in1p.jpg");
        urls.add("https://ws1.sinaimg.cn/large/610dc034ly1fgi3vd6irmj20u011i439.jpg");
        urls.add("https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
        List<CarouselBean> carouselBeanList = new ArrayList<>();
        for(Object url : urls) {
            carouselBeanList.add(new CarouselBean().setPic(url.toString()));
        }

//        ItemLoopImage loopImage = new ItemLoopImage(wSwalfare.getCarousel());
        ItemLoopImage loopImage = new ItemLoopImage(carouselBeanList);
        succeed.add(loopImage);

        FixIntervalBean f1 = new FixIntervalBean(LibApp.findString(R.string.fm_raiders_tutor_fresh));
        succeed.add(f1);

        //礼包
//        succeed.addAll(wSwalfare.getGifts());
        List<RecomWormBean> giftList = new ArrayList<>();
        giftList.add(new RecomWormBean());
        giftList.add(new RecomWormBean());
        succeed.addAll(giftList);

        FixIntervalBean f2 = new FixIntervalBean(LibApp.findString(R.string.item_workshop_walfare_activitys));
        succeed.add(f2);
        //活动
//        succeed.addAll(wSwalfare.getActivitys());

        List<WSwalfare.ActivitysBean> activitysList = new ArrayList<>();
        activitysList.add(new WSwalfare.ActivitysBean());
        activitysList.add(new WSwalfare.ActivitysBean());
        activitysList.add(new WSwalfare.ActivitysBean());
        activitysList.add(new WSwalfare.ActivitysBean());
        activitysList.add(new WSwalfare.ActivitysBean());
        activitysList.add(new WSwalfare.ActivitysBean());
        activitysList.add(new WSwalfare.ActivitysBean());
        activitysList.add(new WSwalfare.ActivitysBean());
        activitysList.add(new WSwalfare.ActivitysBean());
        activitysList.add(new WSwalfare.ActivitysBean());
        succeed.addAll(activitysList);
        mListView.showSucceed(succeed);
    }

    @Override
    public void unsubscribe(){
        clearDisposables();
    }
}