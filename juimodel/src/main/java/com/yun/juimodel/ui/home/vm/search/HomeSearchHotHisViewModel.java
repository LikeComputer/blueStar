package com.yun.juimodel.ui.home.vm.search;

import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.blueprint.helper.SpHelper;
import com.blueprint.helper.ToastHelper;
import com.blueprint.widget.JFlowLayout;
import com.yun.juimodel.BR;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jonas.jlayout.MultiStateLayout;
import jzy.easybindpagelist.statehelper.BaseDiffSteteViewModel;

/**
 * @another 江祖赟
 * @date 2018/1/10.
 */
public class HomeSearchHotHisViewModel extends BaseDiffSteteViewModel implements JFlowLayout.OnItemSelectedListener {

    @Bindable public List<String> hotlist = new ArrayList<>();
    @Bindable public List<String> localHislist = new ArrayList<>();

    private static final String SEARCH_HISTORY = "local_search_history";

    ObservableField<String> selectedStr = new ObservableField<>();
    private ObservableInt mLayoutState;
    public SpHelper mSpHelper;
    private final String mSpKeyofHistory;


    public HomeSearchHotHisViewModel(ObservableField<String> selectedStr, ObservableInt layoutState) {
        this.selectedStr = selectedStr;
        mLayoutState = layoutState;
        //todo String uid = AccountManage.getSingleton().getUid();
        String uid = "default";
        mSpKeyofHistory = SEARCH_HISTORY + "_" + uid;//不同的用户有不同的搜索记录
        mSpHelper = new SpHelper(SEARCH_HISTORY + "_" + uid);
    }


    public void toGetData() {
        //获取本地的历史记录列表
        loadSearchHistory();
        loadHotKeys();
    }



    @Override public void onItemSelected(View v, int position) {
        ToastHelper.showShort(((TextView) v).getText().toString());
        selectedStr.set(((TextView) v).getText().toString());
    }


    public void clearLocalHis() {
        //清除本地记录
        localHislist.clear();
    }


    public void unsubscribe() {
        mSpHelper.put(mSpKeyofHistory, localHislist);
    }


    public void keepSearchKey(String key) {
        //搜索历史记录超过5个依旧显示，正确的应该是5个以前的记录不再显示
        if (!localHislist.contains(key)) {
            if (localHislist.size() >= 5) {
                localHislist.remove(4);
            }
            localHislist.add(0, key);
        }
        else if (localHislist.indexOf(key) > 0) {
            //移动到第一个
            localHislist.remove(key);
            localHislist.add(0, key);
        }
        notifyPropertyChanged(BR.localHislist);
    }

    //public void deleteHisListItem(int position){
    //    mSearchList.remove(position);
    //    if(mSearchList.isEmpty()) {
    //        mView.hideHisSearchList();
    //    }
    //    sp删除 unsubscribe会写入文件
    //}


    /**
     * 根据历史列表的不同数据结构 获取历史数据
     */
    public void loadSearchHistory() {
        StringBuilder orign = new StringBuilder((String) mSpHelper.get(mSpKeyofHistory, ""));
        if (!TextUtils.isEmpty(orign)) {
            if (orign.toString().contains("[]")) {
                clearLocalHis();
            }
            else {
                orign.deleteCharAt(orign.length() - 1).deleteCharAt(0);
                String[] split = orign.toString().split(", ");
                localHislist.addAll(Arrays.asList(split));//Arrays.asList(split)的集合 不可以增删
                notifyPropertyChanged(BR.localHislist);
            }
        }
    }


    public void loadHotKeys() {
        //获取网络 热词数据
        //获取网络热词
        Observable.just(0).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override public void accept(Integer integer) throws Exception {
                hotlist.addAll(Arrays.asList("热刺1", "饥荒", "终结者"));
                notifyPropertyChanged(BR.hotlist);
                //网络数据状态反馈给activity
                mLayoutState.set(MultiStateLayout.LayoutState.STATE_EXCEPT);
            }
        });

        //ServiceFactory.getInstance().createService(MiniWorldApi.class)
        //              .getHotStrings(mSearchType, ParamWrapper.paramWrapper(new HashMap<String,Object>()))
        //              .compose(RxUtill.<MWorldResult<ListWrapper<String>>>defaultSchedulers_obser()).subscribe(new MiniWorldResultConsumer<ListWrapper<String>>() {
        //    @Override
        //    protected void doWhatYouWhant(ListWrapper<String> resultData){
        //        List<String> list = resultData.getList();
        //        mView.showHotSearchKey(list);
        //    }
        //},new MiniErrorHandleConsumer(mView));
    }


    public List<String> getLocalHislist() {
        return localHislist;
    }


    @Override protected void toGetData(HashMap mapParam) {
        toGetData();
    }
}
