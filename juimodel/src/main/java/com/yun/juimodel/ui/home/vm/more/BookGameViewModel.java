package com.yun.juimodel.ui.home.vm.more;

import android.databinding.Bindable;
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
import jzy.easybindpagelist.loadmorehelper.LoadMoreObjectViewModel;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @another 江祖赟
 * @date 2018/1/9.
 */
public class BookGameViewModel extends LoadMoreObjectViewModel {

    @Bindable
    public String firstBgPoster;

    @Override public void toGetData(HashMap mapParam) {
        collectDisposables(Observable.just(0).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override public void accept(Integer integer) throws Exception {
                List<Object> data = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    data.add(new Home.FindGameBean());
                }
                for (int i = 0; i < 5; i++) {
                    data.add(new Home.GameBookBean());
                }
                firstBgPoster= ((Home.FindGameBean) data.get(0)).link;
                notifyPropertyChanged(BR.firstBgPoster);
                refreshedAllData(data);
            }
        }));
    }

    @Override protected void registItemTypes(OnItemBindClass<Object> multipleItems) {
        multipleItems.regist(Home.FindGameBean.class, BR.findGame, R.layout.item_more_findbook_game).regist(Home.GameBookBean.class, BR.findGame, R.layout.item_more_findbook_game);
    }


    public void changeNextData() {
        pageState.set(0);
        mCurrentPage++;
        getDataLists().clear();
        subscribeData(null);
    }
}
