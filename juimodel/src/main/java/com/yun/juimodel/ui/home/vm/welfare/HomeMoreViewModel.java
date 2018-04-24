package com.yun.juimodel.ui.home.vm.welfare;

import com.blueprint.LibApp;
import com.yun.juimodel.BR;
import com.yun.juimodel.R;
import com.yun.juimodel.ui.home.m.Home;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jonas.jlayout.MultiStateLayout;
import jzy.easybindpagelist.loadmorehelper.LoadMoreObjectViewModel;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

public class HomeMoreViewModel extends LoadMoreObjectViewModel {

    {
        pageState.set(MultiStateLayout.LayoutState.STATE_LOADING);
    }

    public String searchHint = LibApp.findString(R.string.more_search_wealfare_gameres);

    @Override protected void registItemTypes(OnItemBindClass<Object> multipleItems) {
        //游戏资源
        multipleItems
                     //游戏资源
                     .regist(Home.ResourceBean.class, BR.gameRes, R.layout.item_more_search_gameres)
                     //玩家推荐 没搜索
                     .regist(Home.UserRecommendBean.class, BR.userRecom, R.layout.item_home_user_recom)
                     //福利中心
                     .regist(Home.WelfareBean.class, BR.wealfare, R.layout.item_more_search_welfare);
    }


    @Override public void toGetData(HashMap mapParam) {

        final List<Object> welfareBeanList = new ArrayList<>();
        welfareBeanList.add(new Home.WelfareBean());
        welfareBeanList.add(new Home.WelfareBean());
        welfareBeanList.add(new Home.WelfareBean());
        welfareBeanList.add(new Home.ResourceBean());
        welfareBeanList.add(new Home.ResourceBean());
        welfareBeanList.add(new Home.ResourceBean());
        welfareBeanList.add(new Home.UserRecommendBean());
        welfareBeanList.add(new Home.UserRecommendBean());

        Observable.just(welfareBeanList).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override public void accept(Object o) throws Exception {

                if (isFirstPage()) {
                    refreshedAllData(welfareBeanList);
                }
                else {
                    addMoreData(welfareBeanList, getDataLists().size() < 30);
                }
            }
        });
    }

}
