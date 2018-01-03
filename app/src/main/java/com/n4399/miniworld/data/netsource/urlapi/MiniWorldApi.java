package com.n4399.miniworld.data.netsource.urlapi;

import com.n4399.miniworld.data.MWorldResult;
import com.n4399.miniworld.data.bean.LoopListBean;
import com.n4399.miniworld.data.bean.WorkShopHome;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @another 江祖赟
 * @date 2017/6/27.
 */
public interface MiniWorldApi {

    public static interface IworkShop {
        //工坊首页-推荐
        @GET("service/workshop/home")
        Observable<MWorldResult<WorkShopHome>> getWSHome();

        //福利中心
        @GET("service/workshop/walfare")
        Observable<MWorldResult<WorkShopHome>> getWSwalfare();

        //联机
        @GET("service/online/getlist")
        Observable<MWorldResult<WorkShopHome>> getWSonline();
    }

    /**
     * 攻略 接口
     */
    public interface raiders {

        /**
         * 小编推荐页，包含轮播图和精选展示
         *
         * @return
         */
        @GET("service/recommend/home")
        Observable<MWorldResult<LoopListBean>> getRecentlyDate(@QueryMap Map<String,String> parma);


    }
}
