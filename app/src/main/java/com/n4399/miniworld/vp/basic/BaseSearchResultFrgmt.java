package com.n4399.miniworld.vp.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.blueprint.basic.common.GeneralListContract;
import com.blueprint.rx.RxBus;
import com.n4399.miniworld.data.event.SearchEvent;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import static com.blueprint.helper.LogHelper.Log_d;
import static com.blueprint.helper.LogHelper.slog_d;

/**
 * @another 江祖赟
 * @date 2017/6/23.
 * 必须复写
 * @Override public void onCreate(@Nullable Bundle savedInstanceState){
 * super.onCreate(savedInstanceState, true);
 * }
 */
public abstract class BaseSearchResultFrgmt<T, D> extends JAbsListFrgmt<T,D> implements GeneralListContract.View<T,D> {

    public String TAG = "BasicSearchFrgmt";
    public String mSearch_key;//非搜索页面 搜索关键字为空
    public CompositeDisposable mCollectDisposable = new CompositeDisposable();

    public void onCreate(@Nullable Bundle savedInstanceState, boolean registKey){
        super.onCreate(savedInstanceState);
        if(registKey)
        //注册 搜索关键字监听
        {
            mCollectDisposable
                    .add(RxBus.getInstance().register(SearchEvent.class).subscribe(new Consumer<SearchEvent>() {
                        @Override
                        public void accept(@NonNull SearchEvent searchEvent) throws Exception{
                            //当前fm可见的时候直接搜索 不可见保存key等可见再搜索
                            mSearch_key = searchEvent.search_key;
                            if(getUserVisibleHint()) {
                                slog_d(TAG, "fm可见 直接发起搜索请求---关键字："+mSearch_key);
                                toDoSearch(searchEvent);
                            }else {
                                slog_d(TAG, "fm不可见 等可见时再发起搜索请求---关键字："+mSearch_key);
                            }
                        }
                    }));
        }
    }

    public void toDoSearch(SearchEvent searchEvent){
        mGeneralListPresenter.search(searchEvent.search_key);
    }

    @Override
    public void firstUserVisibile(){
        if(TextUtils.isEmpty(mSearch_key)) {
            Log_d(TAG, "没有关键字，默认加载全部数据--有关键字就执行搜素");
            //没有搜索关键字 就搜索全部
            super.firstUserVisibile();
        }
    }

    @Override
    public void showLoading(){
        mMultiStateLayout.showStateLoading();
    }

    @Override
    public void showSucceed(List<D> data){
        mSearch_key = "";//清除关键字 当fm再次可见的时候 有关键字就发起搜索请求
        super.showSucceed(data);
    }

    @Override
    public void showError(int eCode){
        super.showError(eCode);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if(mIsViewCreated && isVisibleToUser && !TextUtils.isEmpty(mSearch_key)) {
            Log_d(TAG, "搜索关键字--"+mSearch_key);
            mGeneralListPresenter.search(mSearch_key);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mCollectDisposable.clear();
    }
}
