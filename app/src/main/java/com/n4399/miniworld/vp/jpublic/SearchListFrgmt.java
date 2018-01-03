package com.n4399.miniworld.vp.jpublic;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueprint.widget.JEditText;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import static com.blueprint.helper.StrHelper.nullStrToEmpty;

/**
 * @another 江祖赟
 * @date 2017/6/23.
 * 搜索条+list
 */
public abstract class SearchListFrgmt<D> extends JAbsListFrgmt<D,D> implements JEditText.OnContentClearListener {


    @BindView(R.id.act_raider_synthes_search_et_key) JEditText actRaiderSynthesSearchEtKey;
    private Disposable mSubscribe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        configViews();
        return rootView;
    }

    @Override
    public int setCustomLayout(){
        return R.layout.frgmt_search_list;
    }

    private void configViews(){
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
        actRaiderSynthesSearchEtKey.setHint(nullStrToEmpty(setEditHintContent()));
    }

    protected abstract CharSequence setEditHintContent();

    @Override
    public void showError(int eCode){
        super.showError(eCode);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mSubscribe.dispose();
    }

    @Override
    public void onClear(){
        actRaiderSynthesSearchEtKey.setText("");
        mRecvAdapter.notifyDataSetChanged();
    }
}
