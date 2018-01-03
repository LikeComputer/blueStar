package com.n4399.miniworld.vp.workshop.mapdetail;

import com.blueprint.basic.common.GeneralListContract;
import com.n4399.miniworld.data.bean.HotMapBean;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public interface MapDetailContract {

    interface View extends GeneralListContract.View<HotMapBean,Object> {

    }

    interface Presenter extends GeneralListContract.Presenter {

    }
}