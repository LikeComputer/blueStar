package com.n4399.miniworld.vp.raiders.recommend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueprint.Consistent;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.CarouselBean;
import com.n4399.miniworld.data.bean.ItemLoopImage;
import com.n4399.miniworld.data.bean.MsgCardBean;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;
import com.n4399.miniworld.vp.basic.JBasicWebViewAt;
import com.n4399.miniworld.vp.holderbinder.ItemLoopImageBinder;
import com.n4399.miniworld.vp.holderbinder.ItemMsgCardBinder;
import com.n4399.miniworld.vp.webactivity.WebViewPage;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [小编推荐]
 */
public class RecomFrgmt extends JAbsListFrgmt<MsgCardBean,Object> implements ItemMsgCardBinder.MsgCardItemListener, OnItemClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return new GeneralListPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(ItemLoopImage.class, new ItemLoopImageBinder(this));
        multiTypeAdapter.register(MsgCardBean.class, new ItemMsgCardBinder(this));
    }

    @Override
    public void firstUserVisibile(){
        super.firstUserVisibile();
    }

    @Override
    public void showSucceed(List<Object> data){

        List<String> urls = new ArrayList<>();
        urls.add("https://ws1.sinaimg.cn/large/610dc034ly1fgllsthvu1j20u011in1p.jpg");
        urls.add("https://ws1.sinaimg.cn/large/610dc034ly1fgi3vd6irmj20u011i439.jpg");
        urls.add("https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
        List<CarouselBean> carouselBeanList = new ArrayList<>();
        for(Object url : urls) {
            carouselBeanList.add(new CarouselBean().setPic(url.toString()));
        }
        //        mListData.add(0, new ItemLoopImage(carouselBeanList));
        List<Object> mListData = new ArrayList<>();
        mListData.add(new MsgCardBean("测"));
        mListData.add(new MsgCardBean("测"));
        mListData.add(new MsgCardBean("测"));
        mListData.add(new MsgCardBean("测"));
        super.showSucceed(mListData);
    }

    @Override
    public void onMsgCardItemClicked(MsgCardBean item, int position){
        //        WebViewPage.start(getActivity(),"https://www.baidu.com/");
        WebViewPage.start(getActivity(), item.getUrl());
    }

    @Override
    public void onItemClicked(Object itemData, int position){
        if(itemData instanceof CarouselBean) {
            JBasicWebViewAt.start(getActivity(), Consistent.TEMP.BAIDU);
        }
    }

}
