package com.n4399.miniworld.vp.workshop.recommend;

import com.blueprint.basic.common.GeneralListContract;
import com.n4399.miniworld.data.bean.RecomWormBean;
import com.n4399.miniworld.data.bean.Show2Bean;
import com.n4399.miniworld.data.bean.UnstableBean;

import java.util.List;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [一句话描述]
 */
public class RecomContract {
    interface View extends GeneralListContract.View<Object,Object> {
        void addLoopImageHolder(Show2Bean show2Bean);

        void addUpdateHolder(List<RecomWormBean> wormBeanList);//福利中心和更新

        void addHotHolder(List<UnstableBean> unstableBeanList);

        void addUnstableHolder(List<UnstableBean> unstableBeanList);
    }

    interface IRecoPresenter<T> extends GeneralListContract.Presenter<T> {

        void loadLoopImage();

        void checkUpdate();

        void loadHot();//精选和可控模块是一样的布局

        void loadModule();
    }
}
