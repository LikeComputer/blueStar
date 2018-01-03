package com.n4399.miniworld.vp.raiders.turorial;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.TutorialBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [游戏教程]
 */
public class TutorialFrgmt extends JAbsListFrgmt<TutorialBean,TutorialBean> implements OnItemClickListener<TutorialBean> {

    public static TutorialFrgmt getInstance(){
        return new TutorialFrgmt();
    }

    @Override
    protected GeneralListPresenter<TutorialBean> initListPresenter(){
        return new GeneralListPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(TutorialBean.class, new SimpleRecvBinder(this, R.layout.recv_item_raiders_tutorial));
    }

    @Override
    public void onItemClicked(TutorialBean itemData, int position){
        TutorialDetailAt.start(getActivity(),itemData);
    }

    @Override
    public void showSucceed(List<TutorialBean> data){
        List<TutorialBean> tutorialBeanList = new ArrayList<>();
        tutorialBeanList.add(new TutorialBean());
        tutorialBeanList.add(new TutorialBean());
        tutorialBeanList.add(new TutorialBean());
        tutorialBeanList.add(new TutorialBean());
        tutorialBeanList.add(new TutorialBean());
        tutorialBeanList.add(new TutorialBean());
        super.showSucceed(tutorialBeanList);
    }
}
