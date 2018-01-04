package jzy.easybindpagelist.loadmorehelper;

import android.databinding.Observable;

import java.util.HashMap;
import java.util.List;

import jzy.easybindpagelist.statehelper.StateDiffViewModel;
import me.tatarka.bindingcollectionadapter2.collections.IRecvDataDiff;
import me.tatarka.bindingcollectionadapter2.itembindings.ExtrasBindViewModel;

/**
 * @another 江祖赟
 * @date 2017/12/18.
 * view 中 第一次发起数据请求调用 {@link #toSubscribeData(HashMap)} <br>
 * viewModel 中实际请求网络接口需要实现 {@link #toGetData(HashMap)}<br>
 * {@link #onSubscribeData(Object)}拿到数据成功之后 根据结果调用{@link #refreshedAllData(List)},{@link #addMoreData(List, boolean)}<br>
 * <b>接口返回的数据列表的javaBean需要更新对应的item界面必须继承{@link Observable},使用LoadMoreViewModel2必须继承自{@link ExtrasBindViewModel}</b>
 */
public abstract class LoadMoreViewModel extends AbsLoadMoreViewModel<IRecvDataDiff> {

    private HashMap mOrignMapParam;

    public void toSubscribeData(HashMap mapParam){
        mOrignMapParam = mapParam;
        onSubscribeData(mOrignMapParam);
    }

    public LoadMoreViewModel registOrignParam(HashMap mapParam){
        putOrignParam(mapParam);
        mOrignMapParam = mapParam;
        return this;
    }

    /**
     * {@link #subscribeData(Object)}保留 请求参数之后 会回掉
     * @param orignParam
     */
    @Override
    protected void onSubscribeData(Object orignParam){
        toGetData(mOrignMapParam);
    }

    /**
     * 该方法在本类中 已过时<br>
     *     必须使用{@link #registOrignParam(HashMap)}传入请求参数
     * @param orignParam
     * @return
     */
    @Deprecated
    @Override
    public StateDiffViewModel<List<IRecvDataDiff>> registOrignParam(Object orignParam){
        if(orignParam != null) {
            throw new RuntimeException("该方法在本类中 已过时,必须使用{@link #registOrignParam(HashMap)}传入请求参数");
        }
        return super.registOrignParam(orignParam);
    }

    /**
     * 调用接口 获取数据
     * <p>
     * <B>下拉刷新</B><br>
     * 下拉刷新 会自动调用 {@link #down2RefreshData()},mCurrentPage会重置为初值---》回掉{@link #onSubscribeData(Object)}
     * </p>
     * <p>
     * <b> 上拉加载更多</b><br>
     * 参数 当前页码 {@link #mCurrentPage}直接使用 父类有自动增减
     * <br>上拉之后 {@link #mCurrentPage}自增，同时回掉{@link #onSubscribeData(Object)}
     * </p>
     * <B>拿到数据后需要子类处理</B>
     * <li>数据完整正常 <br>
     * 如果是第一页数据 调用{@link #refreshedAllData(List)}<br>
     * 如果 不是 第一页数据 调用{@link #addMoreData(List, boolean)}同时传入是否还有下一页数据<br>
     * </li>
     * <li>数据异常(空/网络异常) ，直接调用{@link #showPageStateError(int)},或者 {@link #showPageStateError(int, String)}
     * <br>要显示的错误状态{PAGE_STATE_EMPTY,PAGE_STATE_EMPTY}具体看{@link jzy.easybindpagelist.statehelper.PageDiffState}</li>
     */
    protected abstract void toGetData(HashMap mapParam);

    @Override
    protected void refreshedAllData(List<IRecvDataDiff> newData){
        super.refreshedAllData(newData, false);
    }
}
