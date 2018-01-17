package jzy.easybindpagelist.statehelper;

import android.arch.lifecycle.LifecycleOwner;
import java.util.HashMap;

/**
 * @another 江祖赟
 * @date 2018/1/15.
 */
public abstract class BaseDiffSteteViewModel extends StateDiffViewModel<Object> {

    private HashMap mOrignMapParam;


    /**
     * fragment的第一次可见的时候 调用
     * @param mapParam
     */
    public void toSubscribeData(HashMap mapParam) {
        mOrignMapParam = mapParam;
        onSubscribeData(mOrignMapParam);
    }


    /**
     * <b>一般在Activity中使用</b>
     * 请求数据需要的请求参数 该方法和{@link #registLife(LifecycleOwner)} 一起用<br>
     * 同时不要使用{@link #notSubscribeDataWhenCreate()}<br>
     * 也不需要在view模块手动调用{@link #subscribeData(Object)}
     */
    public BaseDiffSteteViewModel registOrignParam(HashMap mapParam) {
        mOrignParam = mOrignMapParam = mapParam;
        return this;
    }


    /**
     * {@link #subscribeData(Object)}保留 请求参数之后 会回掉
     */
    @Override protected void onSubscribeData(Object orignParam) {
        toGetData(mOrignMapParam);
    }


    /**
     * 该方法在本类中 已过时<br>
     * 必须使用{@link #registOrignParam(HashMap)}传入请求参数
     */
    @Deprecated @Override public final StateDiffViewModel<Object> registOrignParam(Object orignParam) {
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
     * <B>拿到数据后需要子类处理</B>
     * <li>数据异常(空/网络异常) ，直接调用{@link #showPageStateError(int)},或者 {@link #showPageStateError(int, String)}
     * <br>要显示的错误状态{PAGE_STATE_EMPTY,PAGE_STATE_EMPTY}具体看{@link jzy.easybindpagelist.statehelper.PageDiffState}</li>
     * <br>推荐使用{@link android.arch.lifecycle.LiveData},在数据模块直接返回liveData,不要保留为成员变量
     */
    protected abstract void toGetData(HashMap mapParam);


    @Override public void showPageStateSuccess(Object data) {
        super.showPageStateSuccess(data);
    }
}
