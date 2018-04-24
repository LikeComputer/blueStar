package com.yun.juimodel.ui.home.vm

import android.util.Log
import com.blueprint.helper.DpHelper
import com.yun.helper.BindingReusePagerAdapter
import com.yun.juimodel.BR
import com.yun.juimodel.R
import com.yun.juimodel.data.commbean.IRoute
import com.yun.juimodel.ui.home.m.Home
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import jzy.easybindpagelist.loadmorehelper.LoadMoreObjectViewModel
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @another 江祖赟
 * @date 2018/1/16.
 */
class HomeViewModel : LoadMoreObjectViewModel() {

    override fun toGetData(mapParam: HashMap<String, Any>?) {
        val tempDataLists = ArrayList<Any>()
        val loopImg = ArrayList<BindingReusePagerAdapter.JVpItem>()
        loopImg.add(IRoute("", "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658"))
        loopImg.add(IRoute("", "http://img.hb.aicdn.com/3c960d0655b6cb9b3edb5ae72f62d682d87c53ef7ec7a-IDgpBF_fw658"))
        loopImg.add(IRoute("", "http://img.hb.aicdn.com/9df1eb685d1eb6ff7939e27308f42a6f4cfe066e4e1cc-S9XVX8_fw658"))
        tempDataLists.add(ItemLoopImgViewModel(loopImg))
        //游戏资源
        val gameRes = ArrayList<Home.ResourceBean>()
        for (i in 1..Random().nextInt(8)) {
            gameRes.add(Home.ResourceBean())
        }
        tempDataLists.add(ItemRecvViewModel(gameRes).asGameRes())
        //发现游戏
        tempDataLists.add(Home.FindGameBean())
        //鉴赏家推荐游戏
        val gameHots = ArrayList<Home.HotRecommendBean>()
        gameHots.add(Home.HotRecommendBean())
        gameHots.add(Home.HotRecommendBean())
        gameHots.add(Home.HotRecommendBean())
        gameHots.add(Home.HotRecommendBean())
        gameHots.add(Home.HotRecommendBean())
        tempDataLists.add(ItemRecvViewModel(gameHots))
        //广告固定在 玩家推荐上
        tempDataLists.add(Home.AdvertisementBean())
        //玩家推荐
        val userRecommendBeanList = ArrayList<Home.UserRecommendBean>()
        userRecommendBeanList.add(Home.UserRecommendBean().castFirst())
        userRecommendBeanList.add(Home.UserRecommendBean())
        userRecommendBeanList.add(Home.UserRecommendBean())
        userRecommendBeanList.add(Home.UserRecommendBean())
        userRecommendBeanList.add(Home.UserRecommendBean().castLast())
        tempDataLists.addAll(userRecommendBeanList)
        //插入游戏推荐
        tempDataLists.add(Home.GameRecommendBean())
        //游戏 预定
        val gameBookList = ArrayList<Home.GameBookBean>()
        gameBookList.add(Home.GameBookBean())
        gameBookList.add(Home.GameBookBean())
        gameBookList.add(Home.GameBookBean())
        gameBookList.add(Home.GameBookBean())
        tempDataLists.add(ItemRecvViewModel(gameBookList).asGameBook())
        //插入游戏推荐
        tempDataLists.add(Home.GameRecommendBean())
        //福利中心
        val welfareBeanList = ArrayList<Home.WelfareBean>()
        welfareBeanList.add(Home.WelfareBean())
        welfareBeanList.add(Home.WelfareBean())
        welfareBeanList.add(Home.WelfareBean())
        tempDataLists.add(ItemRecvViewModel(welfareBeanList).asWelfare())
        //插入游戏推荐
        tempDataLists.add(Home.GameRecommendBean())
        tempDataLists.add(Home.GameRecommendBean())

        Observable.just(0).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe({ _ ->
            println(DpHelper.getScreenHeight())
            if (isFirstPage) {
                refreshedAll2Finish(tempDataLists);
            } else {
                addMoreData(tempDataLists, tempDataLists.size < 30)
            }
        }, { e ->
            pageState.set(3)
            println(Log.getStackTraceString(e))
        })
    }

    override fun registItemTypes(multipleItems: OnItemBindClass<Any>) {

        multipleItems.regist(ItemRecvViewModel::class.java, BR.itemInnerRecv, ItemRecvViewModel.layoutRes)//横向recv
                .regist(ItemImgModel::class.java, BR.itemImgModel, ItemImgModel.layoutRes)
                .regist(Home.UserRecommendBean::class.java, BR.userRecom, R.layout.item_home_user_recom)
                .regist(Home.FindGameBean::class.java, BR.gameFind, R.layout.item_home_gamefind)
                ?.regist(Home.AdvertisementBean::class.java, BR.gamAdv, R.layout.item_home_adv)
                ?.regist(Home.GameRecommendBean::class.java, BR.gameRecom, R.layout.item_home_game_recom)
                ?.regist(ItemLoopImgViewModel::class.java, BR.loopImg, ItemLoopImgViewModel.layoutRes)//轮播图
    }
}