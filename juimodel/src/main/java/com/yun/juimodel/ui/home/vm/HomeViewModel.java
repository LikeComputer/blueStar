//package com.yun.juimodel.ui.home.vm;
//
//import com.yun.helper.BindingReusePagerAdapter;
//import com.yun.juimodel.BR;
//import com.yun.juimodel.R;
//import com.yun.juimodel.data.commbean.IRoute;
//import com.yun.juimodel.ui.home.m.Home;
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.functions.Consumer;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import jzy.easybindpagelist.loadmorehelper.LoadMoreObjectViewModel;
//import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;
//
//public class HomeViewModel extends LoadMoreObjectViewModel {
//
//    {
//        pageState.set(0);
//    }
//
//
//    @Override protected void registItemTypes(OnItemBindClass<Object> multipleItems) {
//        multipleItems.regist(ItemRecvViewModel.class, BR.itemInnerRecv, ItemRecvViewModel.layoutRes)//横向recv
//                     .regist(ItemImgModel.class, BR.itemImgModel, ItemImgModel.layoutRes)
//                     .regist(Home.UserRecommendBean.class, BR.userRecom, R.layout.item_home_user_recom)
//                     .regist(Home.FindGameBean.class, BR.gameFind, R.layout.item_home_gamefind)
//                     .regist(Home.AdvertisementBean.class, BR.gamAdv, R.layout.item_home_adv)
//                     .regist(Home.GameRecommendBean.class, BR.gameRecom, R.layout.item_home_game_recom)
//                     //轮播图
//                     .regist(ItemLoopImgViewModel.class, BR.loopImg, ItemLoopImgViewModel.layoutRes);
//    }
//
//
//    @Override public void toGetData(HashMap mapParam) {
//        final List tempDataLists = new ArrayList();
//        List<BindingReusePagerAdapter.JVpItem> loopImg = new ArrayList<>();
//        loopImg.add(new IRoute("", "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658"));
//        loopImg.add(new IRoute("", "http://img.hb.aicdn.com/3c960d0655b6cb9b3edb5ae72f62d682d87c53ef7ec7a-IDgpBF_fw658"));
//        loopImg.add(new IRoute("", "http://img.hb.aicdn.com/9df1eb685d1eb6ff7939e27308f42a6f4cfe066e4e1cc-S9XVX8_fw658"));
//        tempDataLists.add(new ItemLoopImgViewModel(loopImg));
//        //游戏资源
//        List<Home.ResourceBean> gameRes = new ArrayList<>();
//        gameRes.add(new Home.ResourceBean());
//        gameRes.add(new Home.ResourceBean());
//        gameRes.add(new Home.ResourceBean());
//        gameRes.add(new Home.ResourceBean());
//        gameRes.add(new Home.ResourceBean());
//        gameRes.add(new Home.ResourceBean());
//        gameRes.add(new Home.ResourceBean());
//        tempDataLists.add(new ItemRecvViewModel(gameRes).asGameRes());
//        //发现游戏
//        tempDataLists.add(new Home.FindGameBean());
//        //鉴赏家推荐游戏
//        List<Home.HotRecommendBean> gameHots = new ArrayList<>();
//        gameHots.add(new Home.HotRecommendBean());
//        gameHots.add(new Home.HotRecommendBean());
//        gameHots.add(new Home.HotRecommendBean());
//        gameHots.add(new Home.HotRecommendBean());
//        gameHots.add(new Home.HotRecommendBean());
//        tempDataLists.add(new ItemRecvViewModel(gameHots));
//        //广告固定在 玩家推荐上
//        tempDataLists.add(new Home.AdvertisementBean());
//        //玩家推荐
//        List<Home.UserRecommendBean> userRecommendBeanList = new ArrayList<>();
//        userRecommendBeanList.add(new Home.UserRecommendBean().castFirst());
//        userRecommendBeanList.add(new Home.UserRecommendBean());
//        userRecommendBeanList.add(new Home.UserRecommendBean());
//        userRecommendBeanList.add(new Home.UserRecommendBean());
//        userRecommendBeanList.add(new Home.UserRecommendBean().castLast());
//        tempDataLists.addAll(userRecommendBeanList);
//        //插入游戏推荐
//        tempDataLists.add(new Home.GameRecommendBean());
//        //游戏 预定
//        List<Home.GameBookBean> gameBookList = new ArrayList<>();
//        gameBookList.add(new Home.GameBookBean());
//        gameBookList.add(new Home.GameBookBean());
//        gameBookList.add(new Home.GameBookBean());
//        gameBookList.add(new Home.GameBookBean());
//        tempDataLists.add(new ItemRecvViewModel(gameBookList).asGameBook());
//        //插入游戏推荐
//        tempDataLists.add(new Home.GameRecommendBean());
//        //福利中心
//        List<Home.WelfareBean> welfareBeanList = new ArrayList<>();
//        welfareBeanList.add(new Home.WelfareBean());
//        welfareBeanList.add(new Home.WelfareBean());
//        welfareBeanList.add(new Home.WelfareBean());
//        tempDataLists.add(new ItemRecvViewModel(welfareBeanList).asWelfare());
//        //插入游戏推荐
//        tempDataLists.add(new Home.GameRecommendBean());
//        tempDataLists.add(new Home.GameRecommendBean());
//
//        Observable.just(0).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
//            @Override public void accept(Object o) throws Exception {
//
//                if(isFirstPage()) {
//                    //reflashAll2Finish(tempDataLists);
//                    //refreshedAllData(tempDataLists);
//                } else {
//                    addMoreData(tempDataLists, tempDataLists.size() < 30);
//                }
//            }
//        });
//    }
//}
