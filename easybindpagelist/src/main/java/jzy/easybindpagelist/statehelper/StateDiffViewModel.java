package jzy.easybindpagelist.statehelper;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LiveData;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import jonas.jlayout.MultiStateLayout;
import jonas.jlayout.OnStateClickListener;
import jzy.easybindpagelist.ScrollChildSwipeRefreshLayout;
import me.tatarka.bindingcollectionadapter2.itembindings.ExtrasBindViewModel;

import static android.arch.lifecycle.Lifecycle.State.DESTROYED;
import static jonas.jlayout.MultiStateLayout.LayoutState.STATE_UNMODIFY;
import static me.tatarka.bindingcollectionadapter2.Utils.LOG;

/**
 * @another 江祖赟
 * @date 2017/12/16.
 * 主要处理了页面不同状态的切换<br>
 * 同时作为{@link android.arch.lifecycle.LifecycleObserver} 可以观察{@link LifecycleOwner}即activity和fragment的生命周期<br>
 * 同时作为{@link LifecycleOwner}前提是有监听{@link LifecycleOwner},转发其生命周期,主要是给{@link LiveData}使用{数据层提供liveData不使用回掉}
 */
public abstract class StateDiffViewModel<SD> extends ExtrasBindViewModel implements OnStateClickListener, View.OnLayoutChangeListener, LifecycleOwner, GenericLifecycleObserver {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    protected CharSequence pageStateMsg;
    public ObservableInt pageState = new ObservableInt(MultiStateLayout.LayoutState.STATE_LOADING);
    public ObservableInt pageLoadingRes = new ObservableInt(STATE_UNMODIFY);
    public ObservableInt pageEmptyRes = new ObservableInt(STATE_UNMODIFY);
    public ObservableInt pageErrorRes = new ObservableInt(STATE_UNMODIFY);
    public ObservableInt pageLoadingColorInt = new ObservableInt(STATE_UNMODIFY);
    protected Object mOrignParam;
    protected WeakReference<ScrollChildSwipeRefreshLayout> mSwipeRefreshLayout;
    //转发 View的生命周期
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    /**
     * 显示 SwipeRefreshLayout 刷新状态
     */
    public ObservableBoolean down2Refreshing = new ObservableBoolean(false);
    public ObservableBoolean enableSwipeRefresh = new ObservableBoolean(true);
    private Lifecycle.Event mCurrentEvent;
    /**
     * 开关,注册activity或者fragment后会监听生命周期,在create的时候触发{@link #onSubscribeData(Object)}请求数据
     */
    private boolean mSubscribeDataWhenCreate = true;
    private OnPropertyChangedCallback mPageStateChangeCallback = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable observable, int i){
            //todo loading界面显示的时候 不允许下拉刷新
        }
    };

    {
        //构造代码块    执行顺序 父类--子类
        customMultiStateLayoutRes();
        pageState.addOnPropertyChangedCallback(mPageStateChangeCallback);
    }

    protected void collectDisposables(Disposable disposable){
        mCompositeDisposable.add(disposable);
    }

    protected void clearDisposables(){
        LOG("before-clearDisposables()-: "+mCompositeDisposable.size());
        mCompositeDisposable.clear();
        LOG("after-clearDisposables()-: "+mCompositeDisposable.size());
    }

    /**
     * 自定义 多状态布局的 不同状态的 布局id
     * <li>{@link #pageLoadingRes}</li>
     * <li>{@link #pageEmptyRes}</li>
     * <li>{@link #pageErrorRes}</li>
     * <li>{@link #pageLoadingColorInt}</li>
     */
    protected void customMultiStateLayoutRes(){

    }

    /**
     * <b>在fragment中很多情况是在第一次可见的时候获取数据则调用该方法 主动发起网络请求</b><br>
     * <s>这是删除线</s>
     * 由 view模块 发起请求数据<br>
     * <B>在{@link StateDiffViewModel#registLife(LifecycleOwner)}之后 会自己根据传入的act,frgmt的生命周期在create的时候触发{@link #onSubscribeData(Object)}</B><br>
     * <b>主要是保存 原始请求参数 下拉上拉加载数据的时候需要用到 真正处理数据获取的逻辑需要在{@link #onSubscribeData(Object)}中实现</b>
     *
     * @param orignParam
     */
    public void subscribeData(Object orignParam){
        mSubscribeDataWhenCreate = true;
        mOrignParam = orignParam;
        onSubscribeData(mOrignParam);
    }

    /**
     * <B>在{@link StateDiffViewModel}中会自己根据传入的act,frgmt的生命周期在create的时候触发</B><br>
     * 实际请求数据 实现逻辑<br>
     * {@link #subscribeData(Object)}保留 请求参数之后 会回掉
     * <b>下拉刷新，上拉加载 都是回掉该方法 请求数据的</b>
     * <br>通过使用{@link LiveData}的方式获取数据{@link StateDiffViewModel}就是一个{@link LifecycleOwner}
     *
     * @param orignParam
     */
    protected abstract void onSubscribeData(Object orignParam);

    public void onRefresh(SwipeRefreshLayout swipeRefreshLayout){
        LOG("=========== onRefresh ===========");
        down2Refreshing.set(true);
        mSwipeRefreshLayout = new WeakReference<ScrollChildSwipeRefreshLayout>(( (ScrollChildSwipeRefreshLayout)swipeRefreshLayout ));
        down2RefreshData();
    }

    public void down2RefreshData(){
        onSubscribeData(mOrignParam);
    }

    public void hideLoading(){
        //下拉刷新成功，网络错误重试(下拉不可用--转可用)
        switchSwipeRefresh(true);
        // 隐藏多状态 布局
        pageState.set(MultiStateLayout.LayoutState.STATE_EXCEPT);
    }

    /**
     * 开启下拉刷新  隐藏多状态布局
     *
     * @param data
     */
    public void showPageStateSuccess(SD data){
        hideLoading();
    }

    public void showPageStateLoading(){
        pageState.set(MultiStateLayout.LayoutState.STATE_LOADING);
        pageStateMsg = null;
    }

    public void showPageStateError(@PageDiffState int pageDiffState){
        if(pageDiffState == PageDiffState.PAGE_STATE_EMPTY) {
            LOG("=========== showPageStateError ===========", "数据为空");
            //允许下拉刷新
            switchSwipeRefresh(true);
            //显示 空数据状态
            pageState.set(MultiStateLayout.LayoutState.STATE_EMPTY);
        }else {
            LOG("=========== showPageStateError ===========", "数据异常/网络错误");
            //网络错误数据错误 关闭下拉刷新 因为网络错误有按钮可刷新
            switchSwipeRefresh(false);
            //显示错误界面
            pageState.set(MultiStateLayout.LayoutState.STATE_ERROR);
        }
    }

    /**
     * todo == TT errTips暂时无效
     * @param pageDiffState
     * @param errTips
     */
    public void showPageStateError(@PageDiffState int pageDiffState, String errTips){
        pageStateMsg = errTips;
        //        pageStateMsg = Utils.hightLightStrParser(new SpannableString(errTips), errTips, Color.RED);
        showPageStateError(pageDiffState);
    }

    /**
     * 下拉显示出 下拉圈 回掉onRefresh
     *
     * @param enable
     */
    protected void switchSwipeRefresh(boolean enable){
        down2Refreshing.set(false);
        enableSwipeRefresh.set(enable);
        //        if(mSwipeRefreshLayout != null) {
        //不使用 obverseData 是因为 手动下拉之后 down2Refreshing任然为false,隐藏再次设置为false不会触发notify
        //可以使用 boolean数据 但是在onrefresh中需要重新设置为true 还是用data binding的方式吧
        //            //只有下拉 才出现下拉刷新  或者手动调用setRefresh
        //            mSwipeRefreshLayout.setRefreshing(false);//内部有判断和当前状态是否相同
        //            mSwipeRefreshLayout.setEnabled(enable);//内部有判断和当前状态是否相同
        //        }
    }

    /**
     * 空或者数据异常 的重试
     *
     * @param layoutState
     */
    @Override
    public void onRetry(int layoutState){
        LOG("=========== Retry from stateLayout ===========");
        showPageStateLoading();
        onSubscribeData(mOrignParam);
    }

    @Override
    public void onLoadingCancel(){
        //todo
    }

    /**
     * 获取布局中的控件
     *
     * @param v
     */
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom){
    }

    public void enableSwipeRefreshLayout(boolean enable){
        enableSwipeRefresh.set(enable);
    }

    /**
     * 请求数据需要的请求参数 该方法和{@link #registLife(LifecycleOwner)}一起用<br>
     *     同时不要使用{@link #notSubscribeDataWhenCreate()}<br>
     *          也不需要在view模块手动调用{@link #subscribeData(Object)}
     *
     * @param orignParam
     */
    public StateDiffViewModel<SD> registOrignParam(Object orignParam){
        mOrignParam = orignParam;
        return this;
    }

    //=================== about lifecircle ==========================

    /**
     * 注册LifecycleOwner{activity或者fragment} 监听生命周期<br>
     * 或者直接在activity或者fragment里面把当前viewmodel添加到观察者{getLifecycle().addObserver(viewModel);}
     *
     * @param owner
     * @return
     */
    public StateDiffViewModel<SD> registLife(LifecycleOwner owner){
        if(owner.getLifecycle().getCurrentState() == DESTROYED) {
            // ignore
            return this;
        }
        owner.getLifecycle().addObserver(this);
        return this;
    }


    @NonNull
    @Override
    public Lifecycle getLifecycle(){
        return mLifecycleRegistry;
    }

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event){
        mCurrentEvent = event;
        //转发生命周期
        mLifecycleRegistry.handleLifecycleEvent(event);
        LOG(source, "LifecycleOwner 当前状态", event);
        if(event == Lifecycle.Event.ON_DESTROY) {
            LOG(source, "移除当前生命周期 观察者");
            source.getLifecycle().removeObserver(this);
            pageState.removeOnPropertyChangedCallback(mPageStateChangeCallback);
        }else if(event == Lifecycle.Event.ON_CREATE && mSubscribeDataWhenCreate) {
            //一般情况下fragment会需要在第一次可见的时候发起数据请求,第一次可见并不一定是create的时候
            onSubscribeData(mOrignParam);
        }
    }

    /**
     * 在监听到生命周期的{@link Lifecycle.Event#ON_CREATE} 的时候 不去获取数据<br>
     * 不自动触发{@link #onSubscribeData(Object)}需要在view模块手动调用{@link #subscribeData(Object)}<br>
     *     某些情况下fragment会需要在第一次可见的时候发起数据请求,第一次可见并不一定是create的时候 比如viewpager中
     */
    public StateDiffViewModel<SD> notSubscribeDataWhenCreate(){
        mSubscribeDataWhenCreate = false;
        return this;
    }

    @Override
    protected void onCleared(){
        clearDisposables();
    }

    protected boolean isCurrentDestory(){
        return mCurrentEvent == Lifecycle.Event.ON_DESTROY;
    }
}
