package com.yun.juimodel.ui.headline.vm;

import com.yun.juimodel.data.commbean.MsgCardBean;
import com.yun.juimodel.ui.home.m.GameInfo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jzy.easybindpagelist.loadmorehelper.LoadMoreObjectViewModel;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * 文件描述：
 * 游戏相关头条
 */

public class HeadlinesGameViewModel extends LoadMoreObjectViewModel {
    {
        pageState.set(0);
    }


    @Override protected void registItemTypes(OnItemBindClass<Object> multipleItems) {
        //multipleItems.regist(GameInfo.class, BR.msgCardGameInfo, R.layout.item_headline_sec_gamehead)//轮播图
        //             .regist(MsgCardBean.class, BR.msgCardBean, R.layout.item_headline_sec);
    }


    @Override public void toGetData(HashMap mapParam) {
        final List tempDataLists = new ArrayList();
        tempDataLists.add(new GameInfo());
        //头条
        tempDataLists.add(new MsgCardBean());
        tempDataLists.add(new MsgCardBean());
        tempDataLists.add(new MsgCardBean());
        tempDataLists.add(new MsgCardBean());
        tempDataLists.add(new MsgCardBean());
        tempDataLists.add(new MsgCardBean());
        Observable.just(0).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override public void accept(Object o) throws Exception {

                if (isFirstPage()) {
                    //reflashAll2Finish(tempDataLists);
                    //refreshedAllData(tempDataLists);
                }
                else {
                    addMoreData(tempDataLists, tempDataLists.size() < 30);
                }
            }
        });
    }
}
