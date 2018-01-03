package com.n4399.miniworld.vp.workshop.topic;

import com.blueprint.basic.common.GeneralListContract;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.data.bean.WSfeature;

import java.util.List;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [一句话描述]
 */
public class TopicContract {

    interface View extends GeneralListContract.View<HotMapBean,Object> {

        void showSortGride(List<WSfeature.TypelistBean> sortList);

        void showFeatureMapList(List<HotMapBean> mapList);

        void notifySortChange();
    }

    interface Presenter extends GeneralListContract.Presenter {

        //接口一个 一次拿到所有数据
        void ortsMapListBy(List<HotMapBean> orignList, int orderBy);
    }
}
