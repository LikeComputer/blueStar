package com.n4399.miniworld.vp.live.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueprint.Consistent;
import com.blueprint.LibApp;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.GrideDividerDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.widget.JEditText;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.LiveIngBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;
import com.n4399.miniworld.vp.basic.JBasicWebViewAt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [推荐视频分类]
 */
public class RecoSortDetailFrgmt extends JAbsListFrgmt<LiveIngBean,LiveIngBean> implements
        OnItemClickListener<LiveIngBean>, JEditText.OnContentClearListener {

    @BindView(R.id.act_raider_synthes_search_et_key) JEditText actRaiderSynthesSearchEtKey;
    //请求参数 类型，1：娱乐休闲，2：电路教学，3：生存实况，4：建筑教学
    public static final int LIVE_RECO_XXYL = 1;
    public static final int LIVE_RECO_DLJX = 2;
    public static final int LIVE_RECO_SCSK = 3;
    public static final int LIVE_RECO_JZJX = 4;
    private int mType = LIVE_RECO_XXYL;

    private Disposable mSubscribe;

    public static RecoSortDetailFrgmt getInstance(int type){
        RecoSortDetailFrgmt recoSortDetailFrgmt = new RecoSortDetailFrgmt();
        Bundle bundle = new Bundle();
        bundle.putInt(Consistent.BUND_TAG, type);
        recoSortDetailFrgmt.setArguments(bundle);
        return recoSortDetailFrgmt;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        mType = getArguments().getInt(Consistent.BUND_TAG);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setCustomLayout(){
        return R.layout.frgmt_search_list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        configViews();
        return rootView;
    }

    private void configViews(){
        actRaiderSynthesSearchEtKey.setOnContentClearListener(this);
        actRaiderSynthesSearchEtKey.setHint(R.string.frgmt_live_title);
        //过滤
        mSubscribe = RxTextView.textChangeEvents(actRaiderSynthesSearchEtKey).debounce(500, TimeUnit.MILLISECONDS)
                .filter(new Predicate<TextViewTextChangeEvent>() {
                    @Override
                    public boolean test(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception{
                        return !TextUtils.isEmpty(textViewTextChangeEvent.text());
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception{
                        mGeneralListPresenter.search(textViewTextChangeEvent.text().toString());
                    }
                });
        //        mSubscribe = RxTextView.textChangeEvents(actRaiderSynthesSearchEtKey)
        //                .debounce(500, TimeUnit.MILLISECONDS).filter(new Predicate<TextViewTextChangeEvent>() {
        //                    @Override
        //                    public boolean test(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception{
        //                        return !TextUtils.isEmpty(textViewTextChangeEvent.text());
        //                    }
        //                }).observeOn(AndroidSchedulers.mainThread())
        //                .flatMap(new Function<TextViewTextChangeEvent,ObservableSource<List>>() {
        //                    @Override
        //                    public ObservableSource<List> apply(
        //                            @NonNull final TextViewTextChangeEvent textViewTextChangeEvent) throws Exception{
        //                        return Observable.fromIterable(mListData).filter(new Predicate() {
        //                            @Override
        //                            public boolean test(@NonNull Object o) throws Exception{
        //                                //过滤
        //                                String s = textViewTextChangeEvent.text().toString();
        //                                return true;
        //                            }
        //
        //                        });
        //                    }
        //                }).subscribe(new Consumer<List>() {
        //                    @Override
        //                    public void accept(@NonNull List o) throws Exception{
        //                        //过滤结束
        //                    }
        //                });
    }

    @Override
    protected GeneralListPresenter<LiveIngBean> initListPresenter(){
        return new RecoSortDetailPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new GrideDividerDecoration(LibApp.findDimens(R.dimen.card_pading_8));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(LiveIngBean.class, new SimpleRecvBinder(this,R.layout.recv_item_live_ing));
    }

    @Override
    public void onItemClicked(LiveIngBean itemData, int position){
        JBasicWebViewAt.start(getActivity(),itemData.getUrl());
//        JBasicWebViewAt.start(getActivity(),"http://jaeger.itscoder.com/android/2016/02/14/android-studio-tips.html");
    }

    @Override
    public void showSucceed(List<LiveIngBean> data){
        mListData = new ArrayList<>();
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        mListData.add(new LiveIngBean().setTitle("第一个测试测试测试"));
        super.showSucceed(mListData);
    }

    @Override
    public void onClear(){

    }
}