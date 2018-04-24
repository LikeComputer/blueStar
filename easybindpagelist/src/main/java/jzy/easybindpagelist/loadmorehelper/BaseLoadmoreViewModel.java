package jzy.easybindpagelist.loadmorehelper;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.List;

import jonas.jlayout.MultiStateLayout;
import jzy.easybindpagelist.ScrollChildSwipeRefreshLayout;
import jzy.easybindpagelist.statehelper.PageDiffState;
import jzy.easybindpagelist.statehelper.StateDiffViewModel;
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;
import me.tatarka.bindingcollectionadapter2.collections.IRecvDataDiff;
import me.tatarka.bindingcollectionadapter2.collections.JObservableList;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.ExtrasBindViewModel;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;
import me.tatarka.bindingcollectionadapter2.recv.LayoutManagers;
import me.tatarka.bindingcollectionadapter2.view_adapter.LoadMoreWrapperAdapter;

import static jzy.easybindpagelist.loadmorehelper.LoadmoreFootViewModel.wrapperLoadMoreBinding;
import static me.tatarka.bindingcollectionadapter2.Utils.LOG;

/**
 * @anthor <a href="https://github.com/ZuYun">江祖赟</a>
 * @see
 */
public abstract class BaseLoadmoreViewModel<ID> extends StateDiffViewModel<List<ID>> {

    /**
     * 上拉加载 控制器{ 上拉加载成功/失败/结束/重启}
     */
    public final LoadMoreWrapperAdapter.OnLoadmoreControl mLoadmoreControl = new LoadMoreWrapperAdapter.OnLoadmoreControl() {
        @Override
        protected void onUp2Loadmore(RecyclerView recyclerView, int lastPosition) {
            up2LoadMoreData(recyclerView, lastPosition);
        }


        @Override
        public void onLoadmoreRetry() {
            retryUp2LoadMoreData(null);
        }
    };

    /**
     * 底部 loadmore 布局的viewModel
     */
    protected final LoadmoreFootViewModel mLoadmoreFootViewModel = new LoadmoreFootViewModel(mLoadmoreControl);

    /**
     * 数据容器必须是JObservableList类<br>
     * <b>《接口返回的集合数据必须是{@link ExtrasBindViewModel}的子类 》</b><br>
     * <b>《接口返回的集合数据被添加到{@link #mDataLists}JObservableList通过changecallback通知adapter增删改 》</b><br>
     * <b>《对RecycleView列表的增删改操作只需要对{@link #mDataLists}执行增删改即可 》</b>
     */
    private JObservableList<ID> mDataLists = new JObservableList<>();

    /**
     * 列表数据容器{@link #godLists} 在处理上拉加载的ViewModel 中<br>
     * <b>主要包括两部分:1, 界面的多种类数据{@link #mDataLists}. 2, 底部的 上拉加载提示loading</b>
     * <li><b>{@link #mDataLists} 用来收集存储 展示的所有有效数据(不同类型布局)</b></li>
     * <li><b>{@link #mLoadmoreFootViewModel} 就是底部的上拉加载提示 提示的各种状态(loading,error,finish)主要由
     * {@link LoadMoreWrapperAdapter.OnLoadmoreControl}控制,该控制器主要控制上拉加载的状态</b></li>
     */
    public final MergeObservableList godLists = new MergeObservableList().insertList(mDataLists).insertItem(mLoadmoreFootViewModel);

    /**
     * 注册 不同类型布局及其布局变量ID(data binding传数据给布局的方式通过BR.id方式) 和 对应的class数据类型
     */
    private final OnItemBindClass<ID> multipleItems = wrapperLoadMoreBinding(new OnItemBindClass<>());
    //======  分页 ====
    public static final long FIRST_PAGE = 1;
    /**
     * 静态全局的搜索关键字，简化搜索开发，偷个懒~
     */
    public static String CURRENT_SEARCH_KEY = "";

    protected boolean mIsNetActiveing;

    //    /**
    //     * 存储上一次搜索的关键字，主要用于判断多次执行的搜索动作的关键字是不是同一个
    //     */
    //    private String mLastSearchKey;

    /**
     * 当前页码，调接口传参需要
     */
    public long mCurrentPage = FIRST_PAGE;

    /**
     * 用于下拉刷新全部数据的时候 比较数据差异
     */
    private DiffObservableList mDiffObservableList = new DiffObservableList(mDataLists);

    //todo 不引用 view
    protected WeakReference<RecyclerView> mRecyclerView;
    protected WeakReference<MultiStateLayout> mMultiStateLayout;
    //当列表item数量少于{@link #up2loadmoreMinDataSize}的时候不检测上拉加载
    private int mUp2loadmoreMinDataSize;


    {
        registItemTypes(multipleItems);
        setUp2loadmoreMinDataSize(6);
    }


    /**
     * 不同的layoutmanager复写该方法即可
     */
    public LayoutManagers.LayoutManagerFactory layoutManager() {
        return LayoutManagers.linear();
    }


    /**
     * 注册 不同的 item 类型<br>
     * 缺点是每个item只能绑定一种数据变量 (类--布局--变量)<br>
     * <b>让各自的item数据 去绑定 多余的变量 那么该item的数据 类(类--布局--变量) 必须继承自{@link ExtrasBindViewModel}</b>
     */
    protected abstract void registItemTypes(OnItemBindClass<ID> multipleItems);


    /**
     * <a href="https://developer.android.com/topic/libraries/architecture/viewmodel.html"><b> Caution: A ViewModel must never reference a view,
     * Lifecycle, or any class that may hold a reference to the activity context.</b></a>
     *
     * @param v 主要用来 获取布局中的控件对象<br>
     */
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (v instanceof RecyclerView) {
            mRecyclerView = new WeakReference<RecyclerView>((RecyclerView) v);
            if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.get() != null) {
                mSwipeRefreshLayout.get().setScrollUpChild(v);
            }
        } else if (v instanceof SwipeRefreshLayout) {
            mSwipeRefreshLayout = new WeakReference<ScrollChildSwipeRefreshLayout>((ScrollChildSwipeRefreshLayout) v);
            if (mRecyclerView != null && mRecyclerView.get() != null) {
                mSwipeRefreshLayout.get().setScrollUpChild(mRecyclerView.get());
            }
        } else if (v instanceof MultiStateLayout) {
            mMultiStateLayout = new WeakReference<MultiStateLayout>((MultiStateLayout) v);
        }
    }


    public OnItemBindClass<ID> getMultipleItems() {
        return multipleItems;
    }


    /**
     * 设置默认的底部holder的上拉加载 结束提示语
     */
    protected BaseLoadmoreViewModel<ID> customFootHolderFinishTips(CharSequence finishTips) {
        mLoadmoreControl.mLoadFinishTips = finishTips;
        return this;
    }


    /**
     * 设置默认的底部holder的上拉加载 加载中的提示语
     */
    protected BaseLoadmoreViewModel<ID> customFootHolderLoadingTips(CharSequence loadingTips) {
        mLoadmoreControl.mLoadingTips = loadingTips;
        return this;
    }


    /**
     * 设置默认的底部holder的上拉加载 失败提示语
     */
    protected BaseLoadmoreViewModel<ID> customFootHolderFailTips(CharSequence failTips) {
        mLoadmoreControl.mLoadFailTips = failTips;
        return this;
    }

    // ========================== 上拉加载 逻辑 ==============================


    /**
     * 获取 {@link #mDataLists} 列表显示的items的数据容器列表<BR>
     * 需要显示的列表数据 都需要装载在{@link #mDataLists}中<BR>
     * {@link #mDataLists}的任何增删变化都会通知recycleview的adapter进行相应的增删
     */
    public JObservableList<ID> getDataLists() {
        return mDataLists;
    }


    public boolean isFirstPage() {
        return mCurrentPage == FIRST_PAGE;
    }


    /**
     * 保留请求参数 (参数重试/刷新等时候需要用到)<BR>
     * 不会发起数据请求不触发{@link #onSubscribeData(Object)}
     */
    protected void putOrignParam(Object orignParam) {
        mOrignParam = orignParam;
    }


    protected void checkOrignParam() {
        if (mOrignParam == null) {
            Log.e("BaseLoadmoreViewModel", "******** 传入的参数为null ********");
            //throw new RuntimeException(" oops! you need to putOrignParam(orignParam) in onSubscribeData(orignParam) ...you have to");
        }
    }


    /**
     * 过滤了空数据
     */
    public void search(String key) {
        if (!TextUtils.isEmpty(key)) {
            searchNullAble(key);
        }
    }


    /**
     * 不过滤空数据 空数据可以用来清除搜索<BR>
     * 同时更新 当前正搜索的关键字{@link #CURRENT_SEARCH_KEY}
     *
     * @param key
     */
    public void searchNullAble(@NonNull String key) {
        if (TextUtils.isEmpty(key)) {
            key = "";
        }
        if (!CURRENT_SEARCH_KEY.equals(key)) {
            mCurrentPage = FIRST_PAGE;
            CURRENT_SEARCH_KEY = key;
            showPageStateLoading();
            reset4Search();
            beforeSearchFromService(key);
            toSearchFromService(key);
        } else {
            theSameSearchKey(key);
        }
    }


    /**
     * 可以用来做一些其他的事情，比如：请求参数中添加搜索关键字
     */
    protected void beforeSearchFromService(String key) {

    }


    /**
     * 搜索新关键字的时候 清空列表 <BR>
     * 搜索的时候不清空列表的话 复写该方法即可
     */
    protected void reset4Search() {
        mDataLists.clear();
    }


    protected void theSameSearchKey(String key) { /* 搜索同一个关键字 */ }


    /**
     * 搜索 关键字 复写方法<br>
     * <li>{@link #CURRENT_SEARCH_KEY} 会保存 当前的搜索关键字</li>
     * <li>默认 回掉 {@link #onSubscribeData(Object)}</li>
     * <B>搜索的时候也可以上拉加载 有搜索关键字的时候 上拉加载会触发<br>
     * {@link #up2LoadMoreData(RecyclerView, int)}--->{@link #toSearchFromService(String)}-->{@link #onSubscribeData(Object)}</B><br>
     * <B>无搜索的上拉加载 直接回掉{@link #onSubscribeData(Object)}<br>
     * {@link #up2LoadMoreData(RecyclerView, int)}--->{@link #onSubscribeData(Object)}</B><br>
     */
    protected void toSearchFromService(String key) {
        LOG("=========== toSearchFromService ===========");
        /* 发请求 去搜索关键字吧 */
        onSubscribeData(mOrignParam);
    }

    /**
     * <li>当list的数据被删除部分之后会检测是否需要上拉加载</li> 最好判断lastPosition不要数据很少减少会触发上啦加载
     * <li>手动上拉加载</li>
     *
     * @param recyclerView
     * @param lastPosition
     */
    protected void up2LoadMoreData(RecyclerView recyclerView, int lastPosition) {
        ++mCurrentPage;//建议用索引
        LOG("=========== up2LoadMoreData ===========", mCurrentPage);
        if (!retryUp2LoadMoreData(recyclerView)) {
            //有请求在执行 不出发下一页数据加载 回退  一般出现在列表数据不足一屏幕但是
            mCurrentPage--;
        }
    }


    /**
     * 是否允许在列表数据很少的时候（不足一屏幕）
     *
     * @param lastPosition
     * @return
     */
    protected boolean enableUp2LoadMoreDataWhenFewData(int lastPosition) {
        return lastPosition > mUp2loadmoreMinDataSize;
    }

    /**
     * 检测上拉加载的列表的最少数量，当列表数据少于{@link #mUp2loadmoreMinDataSize}的时候不检测上拉加载
     * @param up2loadmoreMinDataSize
     */
    protected void setUp2loadmoreMinDataSize(int up2loadmoreMinDataSize){
        this.mUp2loadmoreMinDataSize = up2loadmoreMinDataSize;
        mLoadmoreControl.setUp2loadmoreMinDataSize(up2loadmoreMinDataSize);
    }

    /**
     * @param recyclerView
     * @return true进行下一页数据请求
     */
    protected boolean retryUp2LoadMoreData(RecyclerView recyclerView) {
        if (!mIsNetActiveing) {
            mIsNetActiveing = true;
            switchSwipeRefresh(false);//上拉加载的时候关闭下拉刷新 数据成功之后再开启
            LOG("=========== retryUp2LoadMoreData ===========", mCurrentPage);
            if (mCurrentPage > FIRST_PAGE) {
                //上拉加载 失败 重试 一定不是在第一页的时候
                if (TextUtils.isEmpty(CURRENT_SEARCH_KEY)) {
                    //关键字为空 非搜索
                    onSubscribeData(mOrignParam);
                } else {
                    toSearchFromService(CURRENT_SEARCH_KEY);
                }
            }
            return true;
        } else {
            LOG("=========== retryUp2LoadMoreData 有网络请求进行中 上拉取消===========", mCurrentPage);
            return false;
        }
    }


    @Override
    public void onRefresh(SwipeRefreshLayout swipeRefreshLayout) {
        mLoadmoreControl.forceDown2Refresh();//需要重启 底部loading
        super.onRefresh(swipeRefreshLayout);
    }


    /**
     * 布局中swipeRefreshView的onRefresh调用
     */
    @Override
    public void down2RefreshData() {
        if (!mIsNetActiveing) {
            mIsNetActiveing = true;
            mCurrentPage = FIRST_PAGE;
            LOG("=========== down2RefreshData ===========", mCurrentPage);
            //显示 第一页的 loading 状态 下拉刷新 不显示 不然会同时显示 下拉刷新的圆圈
            //        showPageStateLoading();
            if (TextUtils.isEmpty(CURRENT_SEARCH_KEY)) {
                //关键字为空 非搜索
                onSubscribeData(mOrignParam);
            } else {
                toSearchFromService(CURRENT_SEARCH_KEY);
            }
        } else {
            LOG("=========== down2RefreshData 有网络请求进行中 下拉取消===========", mCurrentPage);
        }

    }


    protected void removeFootLoadmoreItem() {
        mLoadmoreControl.setEnableUp2LoadMore(false);
        godLists.removeItem(mLoadmoreFootViewModel);
    }


    protected void restoreFootLoadmoreItem() {
        mLoadmoreControl.setEnableUp2LoadMore(true);
        godLists.insertItem(mLoadmoreFootViewModel);
    }


    /**
     * todo 将数据刷新到mDataList 不是第一页需要判断是否有下一页,需要留意 数据可能就只有一页
     */
    @Override
    public void showPageStateSuccess(List<ID> listData) {
        super.showPageStateSuccess(listData);
        //子类注意重写 addMoreData逻辑 不然一直可以无限上拉加载
        if (mCurrentPage == FIRST_PAGE) {
            refreshedAllData(listData);
            //第一页也可能就结束了,数据可能就一页
        } else {
            addMoreData(listData);
        }
        mIsNetActiveing = false;
    }


    /**
     * 重新 刷新 到 第一页数据<br>
     * 更新全部数据 回到第一页<br>
     * List 的数据元素 建议继承  {@link ExtrasBindViewModel}，当然也可以直接继承{@link BaseObservable}
     */
    protected void refreshedAllData(List<ID> newData) {
        checkOrignParam();
        LOG("=========== refreshedAllData ===========", newData.size());
        if (mDataLists.isEmpty()) {
            mDataLists.addAll(newData);
        } else {
            mDataLists.changeAll(newData);
        }
        if (!enableUp2LoadMoreDataWhenFewData(newData.size())) {
            mLoadmoreControl.loadmoreFinished();
        }
        hideLoading();
    }


    /**
     * 只有在 newData的元素是{@link IRecvDataDiff}的子类时 detectMoves 才有效
     */
    protected final void refreshedAllData(List<ID> newData, boolean detectMoves) {
        checkOrignParam();
        LOG("=========== refreshedAllData ===========", newData.size());
        if (mDataLists.isEmpty()) {
            mDataLists.addAll(newData);
        } else {
            //newData 不一定是 IRecvDataDiff 的子类  差异计算必须是IRecvDataDiff的子类
            if (!mDiffObservableList.set(mDataLists).detectMoves(detectMoves).update(newData)) {
                mDataLists.changeAll(newData);
            }
        }
        //数据少于 最少的数据 直接显示加载完成
        if (!enableUp2LoadMoreDataWhenFewData(newData.size())) {
            mLoadmoreControl.loadmoreFinished();
        }
        hideLoading();
    }


    protected void refreshedAll2Finish(List<ID> newData, boolean detectMoves) {
        refreshedAllData(newData, detectMoves);
        mLoadmoreControl.loadmoreFinished();
    }


    protected void refreshedAll2Finish(List<ID> newData) {
        refreshedAllData(newData);
        mLoadmoreControl.loadmoreFinished();
    }


    @Override
    public void hideLoading() {
        super.hideLoading();
        mIsNetActiveing = false;
    }


    /**
     * 上拉加载结束了 没有更多可加载了
     */
    protected void noMore2Load() {
        mLoadmoreControl.loadmoreFinished();
        mIsNetActiveing = false;
    }


    protected void addItemData(ID item) {
        mDataLists.add(item);
        mIsNetActiveing = false;
    }


    protected void addMoreData(List<ID> moreData) {
        addMoreData(moreData, true, null);
    }


    protected void addMoreData(List<ID> moreData, boolean hasNext, String tips) {
        LOG("=========== addMoreData ===========", hasNext, tips);
        mDataLists.addAll(moreData);
        mIsNetActiveing = false;
        mLoadmoreControl.setLoadmoreFinished(!hasNext, tips);//有下一页 finish就是false不结束
        switchSwipeRefresh(true);
    }


    protected void addMoreData(List<ID> moreData, boolean hasNext) {
        addMoreData(moreData, hasNext, null);
    }


    @Override
    public void showPageStateError(@PageDiffState int pageDiffState) {
        if (mCurrentPage == FIRST_PAGE) {
            //网络错误等 加载数据失败
            super.showPageStateError(pageDiffState);
        } else {
            //上拉加载 失败
            mLoadmoreControl.loadMoreFail();
            switchSwipeRefresh(true);
        }
        mIsNetActiveing = false;
    }


    @Override
    public void showPageStateError(@PageDiffState int pageDiffState, String errTips) {
        if (mCurrentPage == FIRST_PAGE) {
            //网络错误等 加载数据失败
            super.showPageStateError(pageDiffState, errTips);
        } else {
            //上拉加载 失败
            mLoadmoreControl.loadMoreFail(errTips);
            switchSwipeRefresh(true);
        }
        mIsNetActiveing = false;
    }

    /**
     * 设置底部loading的提示语
     *
     * @param tips
     */
    protected final void resetLoadingTips(CharSequence tips) {
        mLoadmoreControl.configLoadingTips(tips);
        mLoadmoreFootViewModel.switch2Loading(tips);//需要触发激活下
    }

    @Override
    public void onRetry(int layoutState) {
        pageState.set(0);
        down2RefreshData();
    }
}
