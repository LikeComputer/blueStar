package com.n4399.miniworld.vp.jpublic;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blueprint.adapter.BaseRecvAdapter;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.basic.JBasePresenter;
import com.blueprint.helper.CheckHelper;
import com.blueprint.helper.DpHelper;
import com.blueprint.helper.KeyboardHelper;
import com.blueprint.helper.LogHelper;
import com.blueprint.rx.RxBus;
import com.blueprint.widget.JFlowLayout;
import com.blueprint.widget.JEditText;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.event.SearchEvent;
import com.n4399.miniworld.vp.basic.JBaseTitleActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import static com.blueprint.Consistent.ErrorCode.ERROR_EMPTY;
import static com.blueprint.helper.StrHelper.nullStrToEmpty;

public abstract class BaseSearchAt<D> extends JBaseTitleActivity implements SearchContract.View<D>, JEditText.OnContentClearListener {

    @BindView(R.id.act_raider_search_et_key) protected JEditText actRaiderSearchEtKey;
    @BindView(R.id.act_raider_search_his_clear) protected TextView actRaiderSearchHisClear;
    @BindView(R.id.act_raider_search_hislist) protected RecyclerView actRaiderSearchHislist;
    @BindView(R.id.at_raider_ll_search_his) protected LinearLayout actRaiderSearch;
    @BindView(R.id.act_raider_search_hot_layout) protected JFlowLayout actRaiderSearchHotLayout;
    @BindView(R.id.raiders_search_history_hot) protected LinearLayout mSearchHisHotLayout;//不现实 搜索热词只保留第一个子view即可

    public BaseRecvAdapter<D> mHisListAdapter;
    public SearchPresenter mSearchPresenter;
    private Disposable mSubscribe;

    @Override
    protected JBasePresenter initPresenter(){
        return mSearchPresenter = initSearchListPresenter();
    }

    protected abstract SearchPresenter initSearchListPresenter();

    @Override
    protected boolean requestNoTitleBar(){
        //布局重写了titlebar 此处不需要titlebar
        return true;
    }

    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout container){
        View rootView = inflater.inflate(R.layout.activity_raider_search, container);
        ButterKnife.bind(this, rootView);
        initRecycleview();
        configViews();
    }

    protected void initRecycleview(){
        mHisListAdapter = setHistoryListAdapter();
        actRaiderSearchHislist.setLayoutManager(new LinearLayoutManager(this));
        actRaiderSearchHislist.setAdapter(mHisListAdapter);
    }

    protected void configViews(){
        actRaiderSearchEtKey.setHint(nullStrToEmpty(setSearchHint()));
        actRaiderSearchEtKey.setOnContentClearListener(this);
        //通过rxbus通知fragment刷新数据
        mSubscribe = RxTextView.textChangeEvents(actRaiderSearchEtKey).debounce(500, TimeUnit.MILLISECONDS)
                .filter(new Predicate<TextViewTextChangeEvent>() {
                    @Override
                    public boolean test(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception{
                        return !TextUtils.isEmpty(textViewTextChangeEvent.text());
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<TextViewTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewTextChangeEvent textViewTextChangeEvent) throws Exception{
                        mSearchPresenter.searchByKey(textViewTextChangeEvent.text().toString());
                        mSearchHisHotLayout.setVisibility(View.GONE);
                        LogHelper.Log_d("搜索关键字："+textViewTextChangeEvent.text().toString());
                        actSearch(textViewTextChangeEvent.text().toString());
                    }
                });
        //热词 布局点击事件
        actRaiderSearchHotLayout.setOnItemSelectedListener(new JFlowLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View v, int position){
                CheckedTextView textView = (CheckedTextView)v;
                if(textView.isChecked()) {
                    doSearch(textView.getText().toString());
                }
            }
        });
        actRaiderSearchHotLayout.setItemBackgroundResource(R.drawable.search_item_selector)
                .setVerticalSpacing((int)DpHelper.dp2px(16)).setHorizontalSpacing((int)DpHelper.dp2px(14))
                .setItemTvColor(Color.parseColor("#999999")).setSingleSelected(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.raiders_search_result, replaceResultFrgmt())
                .commit();
    }

    protected abstract Fragment replaceResultFrgmt();

    /**
     * 点击历史列表/热词 触发的搜索 --设置edittext内容 触发监听 调用搜索 actSearch
     *
     * @param text
     */
    protected void doSearch(String text){
        //内容 不同才执行搜索动作
        if(!text.equals(actRaiderSearchEtKey.getText().toString())) {
            actRaiderSearchEtKey.setText(text);
            actRaiderSearchEtKey.setSelection(text.length());
        }
    }

    protected BaseRecvAdapter setHistoryListAdapter(){
        return new BaseRecvAdapter<String>(new ArrayList<String>(), R.layout.comon_item_search_history) {
            @Override
            public void convert(final RecyclerHolder holder, final int position, final String itemData){
                RxView.clicks(holder.getView(R.id.common_img_search_history_del)).debounce(300, TimeUnit.MILLISECONDS)
                        .subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(@NonNull Object o) throws Exception{
                                delHisListSearchItem(holder.getLayoutPosition());
                            }
                        });
                holder.setText(R.id.common_item_search_tv_title, itemData);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        mHisListAdapter.notifyItemMoved(holder.getLayoutPosition(), 0);
                        doSearch(itemData);
                    }
                });
            }
        };
    }

    protected abstract CharSequence setSearchHint();

    /**
     * 调用接口获取索搜内容
     *
     * @param key
     */
    public void actSearch(String key){
        RxBus.getInstance().post(new SearchEvent(key));
    }

    @OnClick({R.id.act_raider_search_his_clear})
    public void onViewClicked(View view){
        switch(view.getId()) {
            case R.id.act_raider_search_his_clear:
                mSearchPresenter.clearHistory();
                break;
        }
    }


    @Override
    public void showLoading(){
        mMultiStateLayout.showStateLoading();
    }


    @Override
    public void showSucceed(D data){
        mMultiStateLayout.showStateSucceed();
    }


    @Override
    public void showError(int eCode){
        if(eCode == ERROR_EMPTY) {
            mMultiStateLayout.showStateEmpty();
        }else {
            mMultiStateLayout.showStateError();
        }
    }


    @Override
    public void showHisSearchList(List<D> keys){
        actRaiderSearch.setVisibility(View.VISIBLE);
        mHisListAdapter.refreshDataChange(keys);
    }

    @Override
    public void showHotSearchKey(List<String> keys){
        showSucceed(null);
        if(CheckHelper.checkLists(keys)) {
            actRaiderSearchHotLayout.addContent(keys);
        }else {
            LinearLayout parent = (LinearLayout)actRaiderSearch.getParent();
            //不存在热词 就移除热词布局 只保留搜索历史列表
            for(; parent.getChildCount()>1; ) {
                parent.removeViewAt(1);
            }
        }
    }

    @Override
    public void hideHisSearchList(){
        actRaiderSearch.setVisibility(View.GONE);
    }

    @Override
    public void delHisListSearchItem(int position){
        //删除 记录
        //        mHisListAdapter.removeItem(position);//会删除数据 adapter里面的list和presenter里面的是同一个对象
        mHisListAdapter.notifyItemRemoved(position);//不删除数据 历史数据维护再presenter里面 presenter处理删除数据
        mSearchPresenter.deleteHisListItem(position);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                KeyboardHelper.toggleKeyboard();
                break;
        }
        return super.onTouchEvent(event);
    }

    public void backfinish(View v){
        onBackPressed();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mSearchPresenter.unsubscribe();
        //        RxBus.getInstance().unregisterAll();
        mSubscribe.dispose();
    }

    @Override
    public void onClear(){
        //搜索框内容清空回掉
        mSearchHisHotLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed(){
        if(mSearchHisHotLayout.getVisibility() == View.VISIBLE) {
            super.onBackPressed();
        }else {
            mSearchHisHotLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(mSearchHisHotLayout.getVisibility() == View.GONE) {
                    KeyboardHelper.hideKeyboard(this);
                }
        }
        return super.dispatchTouchEvent(event);
    }
}
