package com.n4399.miniworld.vp.raiders.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.MsgCardBean;
import com.n4399.miniworld.data.event.SearchEvent;
import com.n4399.miniworld.vp.basic.BaseSearchResultFrgmt;
import com.n4399.miniworld.vp.basic.JBasicWebViewAt;
import com.n4399.miniworld.vp.holderbinder.ItemMsgCardBinder;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

import static com.n4399.miniworld.Constants.CKEY_FRGMT;
import static com.n4399.miniworld.Constants.RaidersKey.FM_SEARCHRESULT;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [新闻资讯/文字攻略/适配攻略]
 */
public class NewsVideoFrgmt extends BaseSearchResultFrgmt<MsgCardBean,MsgCardBean> implements ItemMsgCardBinder.MsgCardItemListener {

    private String mType;
    private ItemMsgCardBinder mItemMsgCardBinder;


    public static Fragment getInstance(){
        return new NewsVideoFrgmt();
    }

    @Override
    protected GeneralListPresenter<MsgCardBean> initListPresenter(){
        TAG = "NewsVideoFrgmt 新闻资讯/文字攻略/适配攻略";
        return new GeneralListPresenter<MsgCardBean>(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        if(getArguments() != null) {
            mType = getArguments().getString(CKEY_FRGMT);
        }
        super.onCreate(savedInstanceState, FM_SEARCHRESULT.equals(mType));
    }


    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new LinearLayoutManager(getContext());
    }


    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        mItemMsgCardBinder = new ItemMsgCardBinder(this);
        multiTypeAdapter.register(MsgCardBean.class, mItemMsgCardBinder);
    }


    @Override
    public void toDoSearch(SearchEvent searchEvent){
        mItemMsgCardBinder.searchKey(mSearch_key);
        mGeneralListPresenter.search(searchEvent.search_key);
    }

    @Override
    public void onLoadingMore(){
        super.onLoadingMore();
    }


    @Override
    public void showLoading(){
        mMultiStateLayout.showStateLoading();
    }


    @Override
    public void showSucceed(List<MsgCardBean> data){
        mListData = new ArrayList<>();
        mListData.add(new MsgCardBean("测"));
        mListData.add(new MsgCardBean("鲨鱼吧"));
        mListData.add(new MsgCardBean("啊啦傻瓜"));
        mListData.add(new MsgCardBean("向客户该功能"));
        mListData.add(new MsgCardBean("瞎几把搞"));
        mListData.add(new MsgCardBean("测试很快"));
        mListData.add(new MsgCardBean("今天天气好"));
        mListData.add(new MsgCardBean("今天不刷怪"));
        super.showSucceed(mListData);
    }


    @Override
    public void onMsgCardItemClicked(MsgCardBean item, int position){
        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
        JBasicWebViewAt.start(getActivity(), item.getUrl());
    }


    @Override
    public void onMoreLoad(List<MsgCardBean> containerData){
        super.onMoreLoad(containerData);
    }

}
