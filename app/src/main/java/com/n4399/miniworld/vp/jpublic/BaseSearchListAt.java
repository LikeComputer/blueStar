package com.n4399.miniworld.vp.jpublic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.blueprint.widget.JEditText;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JAbsListActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * @another [https://github.com/ZuYun]
 * @desc [search+list]
 */
public abstract class BaseSearchListAt<D> extends JAbsListActivity<D,D> implements JEditText.OnContentClearListener {

    @BindView(R.id.act_raider_synthes_search_et_key) protected JEditText actRaiderSynthesSearchEtKey;
    private Disposable mSubscribe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        configViews();
    }

    protected void configViews(){
        actRaiderSynthesSearchEtKey.setOnContentClearListener(this);
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
    }

    @Override
    public int setCustomLayout(){
        return R.layout.frgmt_search_list;
    }

    @Override
    public void onClear(){
        actRaiderSynthesSearchEtKey.setText("");
        mGeneralListPresenter.search("");
    }
}