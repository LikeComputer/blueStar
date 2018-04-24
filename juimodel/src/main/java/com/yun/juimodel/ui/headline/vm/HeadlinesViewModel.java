package com.yun.juimodel.ui.headline.vm;

import com.yun.helper.BindingReusePagerAdapter;
import com.yun.juimodel.BR;
import com.yun.juimodel.R;
import com.yun.juimodel.data.commbean.IRoute;
import com.yun.juimodel.data.commbean.MsgCardBean;
import com.yun.juimodel.ui.home.vm.ItemLoopImgViewModel;
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
 * 游戏头条首页
 */

public class HeadlinesViewModel extends LoadMoreObjectViewModel {
    {
        pageState.set(0);
    }

    @Override protected void registItemTypes(OnItemBindClass<Object> multipleItems) {
        multipleItems.regist(ItemLoopImgViewModel.class, BR.loopImg, ItemLoopImgViewModel.layoutRes)//轮播图
                     .regist(MsgCardBean.class, BR.msgCardBean, R.layout.item_headline_first);
    }


    @Override public void toGetData(HashMap mapParam) {
        final List tempDataLists = new ArrayList();
        List<BindingReusePagerAdapter.JVpItem> loopImg = new ArrayList<>();
        loopImg.add(new IRoute("", "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658"));
        loopImg.add(new IRoute("", "http://img.hb.aicdn.com/3c960d0655b6cb9b3edb5ae72f62d682d87c53ef7ec7a-IDgpBF_fw658"));
        loopImg.add(new IRoute("", "http://img.hb.aicdn.com/9df1eb685d1eb6ff7939e27308f42a6f4cfe066e4e1cc-S9XVX8_fw658"));
        tempDataLists.add(new ItemLoopImgViewModel(loopImg));
        //热搜
        //
        tempDataLists.add(new MsgCardBean());
        tempDataLists.add(new MsgCardBean());
        tempDataLists.add(new MsgCardBean());
        tempDataLists.add(new MsgCardBean());
        tempDataLists.add(new MsgCardBean());
        tempDataLists.add(new MsgCardBean());
        Observable.just(0).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override public void accept(Object o) throws Exception {

                if (isFirstPage()) {
                    refreshedAllData(tempDataLists);
                }
                else {
                    addMoreData(tempDataLists, tempDataLists.size() < 30);
                }
            }
        });
    }
}
