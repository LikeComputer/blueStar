package com.n4399.miniworld.vp.workshop.recommend;

import com.blueprint.Consistent;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.data.bean.RecomWormBean;
import com.n4399.miniworld.data.bean.Show2Bean;
import com.n4399.miniworld.data.bean.UnstableBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [一句话描述]
 */
public class RecomPresenter extends GeneralListPresenter implements RecomContract.IRecoPresenter {

    private RecomContract.View mView;

    public RecomPresenter(RecomContract.View view){
        super(view);
        mView = view;
    }

    @Override
    public void subscribe(Object o){
        Logger.d("工坊推荐tab --- sunscribe");
        mView.showLoading();
        loadData();
    }

    private void loadData(){
        //加载 推荐广告
        loadLoopImage();
        //判断是否需要更新下载
        checkUpdate();
        //加载 精选
        loadHot();
        //加载 插卡模块
        loadModule();
        //        ServiceFactory.getInstance().createService(GankService.class).getGanHuo("福利", 1)
        //                .compose(RxUtill.<HttpResult<List<GanHuoData>>>defaultSchedulers_single())
        //                .subscribe(new Consumer<HttpResult<List<GanHuoData>>>() {
        //                    @Override
        //                    public void accept(@NonNull HttpResult<List<GanHuoData>> listHttpResult) throws Exception{
        //                        List<String> urls = new ArrayList<String>();
        //                        for(GanHuoData result : listHttpResult.results) {
        //                            urls.add(result.getUrl());
        //                        }
        //
        //                        mView.addLoopImageHolder(urls);
        //                        mView.showSucceed(null);
        //                    }
        //                }, new Consumer<Throwable>() {
        //                    @Override
        //                    public void accept(@NonNull Throwable throwable) throws Exception{
        //                    }
        //                });
        mView.showSucceed(null);
    }

    @Override
    public void unsubscribe(){
        Logger.d("RecomPresenter - unsubscribe");
        mCompositeDisposable.clear();
        mView = null;
    }

    @Override
    public void loadLoopImage(){
        Show2Bean show2Bean = new Show2Bean();
        List<Show2Bean.ShowBean> showBeanList = new ArrayList<>();
        showBeanList.add(new Show2Bean.ShowBean());
        showBeanList.add(new Show2Bean.ShowBean());
        show2Bean.setShow(showBeanList);
        mView.addLoopImageHolder(show2Bean);
    }

    @Override
    public void checkUpdate(){
        List<RecomWormBean> list = new ArrayList<>();
        RecomWormBean recomWormBean = new RecomWormBean();
        recomWormBean.setType(RecomWormBean.TYPE_WELFARE);
        recomWormBean.setRecDesc("福利中心测试");
        list.add(recomWormBean);
        mView.addUpdateHolder(list);
    }

    @Override
    public void loadHot(){
        List<UnstableBean> list = new ArrayList<>();
        UnstableBean unstate = new UnstableBean();
        unstate.setTitle("精选地图");
        List<HotMapBean> hopmaplist = new ArrayList<>();

        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());

        unstate.setList(hopmaplist);
        list.add(unstate);
        mView.addHotHolder(list);
    }

    @Override
    public void loadModule(){
        List<UnstableBean> list = new ArrayList<>();
        UnstableBean unstate = new UnstableBean();
        unstate.setTitle("可控模块,插卡");
        unstate.setPic(Consistent.TEMP.PIC1);
        List<HotMapBean> hopmaplist = new ArrayList<>();

        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());
        hopmaplist.add(new HotMapBean());

        unstate.setList(hopmaplist);
        list.add(unstate);
        mView.addHotHolder(list);
    }
}
