package jzy.easybind.bindstar.viewmodel;


import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import jzy.easybind.BR;
import jzy.easybind.R;
import jzy.easybindpagelist.loadmorehelper.LoadMoreViewModel;
import jzy.easybindpagelist.statehelper.PageDiffState;
import me.tatarka.bindingcollectionadapter2.collections.IRecvDataDiff;
import me.tatarka.bindingcollectionadapter2.collections.JObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.ExtrasBindViewModel;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

public class PageRecvViewModel extends LoadMoreViewModel {

    @Override
    public void customMultiStateLayoutRes() {
//        pageLoadingColorInt.set(Color.YELLOW);
        pageLoadingRes.set(R.layout.activity_main);
    }

    @Override
    protected void registItemTypes(OnItemBindClass<IRecvDataDiff> multipleItems) {
        multipleItems.regist(ItemRecvViewModel.class, BR.recvItem, ItemRecvViewModel.layoutRes).regist(ItemImgModel.class, BR.itemImgModel, ItemImgModel.layoutRes)
//                .regist(ItemViewModel.class, ItemBindModel.of(BR.itemViewModel, ItemViewModel.layoutRes).bindExtra())
                .regist(ItemViewModel.class, BR.itemViewModel, ItemViewModel.layoutRes);
        resetLoadingTips("自定义加载");
    }

    @Override
    public void toGetData(HashMap mapParam) {

        final JObservableList<IRecvDataDiff> newData = new JObservableList<IRecvDataDiff>();
        if (!getDataLists().isEmpty()) {
            int a = new Random().nextInt(refreshList.size() - 2);
            int b = new Random().nextInt(refreshList.size() - 3);
            if (a != b) {
//                refreshList.swap(a, b);
                refreshList.remove(a);
            } else {
                refreshList.remove(a);
            }
            newData.addAll(refreshList);
        } else {
            for (int i = 0; i < 11; i++) {
                newData.add(new ItemViewModel(this, "抓取的数据" + (getDataLists().size() + i)));
            }
        }
        if (getDataLists().isEmpty()) {
            newData.add(0, new ItemRecvViewModel());

        }

        collectDisposables(Observable.just(1).delay(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.functions.Consumer<Integer>() {
            @Override
            public void accept(Integer jObservableList) throws Exception {
                if (isFirstPage()) {
                    refreshedAllData(newData, true);
                } else {
                    if (new Random().nextBoolean()) {
                        addMoreData(newData, getDataLists().size() < 30, "自定义提示信息");
                    } else {
                        showPageStateError(PageDiffState.PAGE_STATE_ERROR);
                    }
                }
            }
        }));
    }


    private JObservableList<ExtrasBindViewModel> refreshList = new JObservableList<ExtrasBindViewModel>();

    public void deleItem(ItemViewModel o) {
        if (new Random().nextBoolean()) {
            int index = new Random().nextInt(5);
            getDataLists().change(index, new ItemViewModel("change修改的数据 ^_^"), 0);
            ((ItemViewModel) getDataLists().get(index+1)).setContent("变啦" + new Random().nextBoolean());
        } else {
            getDataLists().remove(o);
        }
    }

    public PageRecvViewModel() {
        for (int i = 0; i < 11; i++) {
            refreshList.add(new ItemViewModel(this, "下拉刷新的数据 " + i));
        }
    }

    public boolean addItem() {
        getDataLists().add(new Random().nextInt(9), new ItemViewModel(this, "新增数据" + new Random().nextInt()));
        return true;
    }


    public void removeItem() {
        if (getDataLists().size() == 11) {
            getDataLists().clear();
        } else if (getDataLists().size() == 12) {
            getDataLists().clear();
            getDataLists().add(new ItemViewModel("新增数据 1"));
            getDataLists().add(new ItemRecvViewModel());
            getDataLists().add(new ItemViewModel("新增数据 2"));
        } else if (getDataLists().size() > 1) {
            getDataLists().remove(getDataLists().size() - 1);
        }
    }

}
