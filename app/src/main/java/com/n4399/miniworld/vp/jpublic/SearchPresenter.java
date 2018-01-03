package com.n4399.miniworld.vp.jpublic;

import com.blueprint.helper.SpHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.n4399.miniworld.Constants.SharePrenceKey.SEARCH_HISTORY;
import static com.n4399.miniworld.Constants.SharePrenceKey.SEARCH_HISTORY_RAIDERS;

/**
 * @another 江祖赟
 * @date 2017/6/22.
 * 只处理搜索记录和关键字保存
 */
public class SearchPresenter<D> implements SearchContract.Presenter {
    public SearchContract.View mView;
    private List<String> mSearchList = new ArrayList<>();
    private List<String> mHotList = new ArrayList<>();
    public SpHelper mSpHelper;

    public SearchPresenter(SearchContract.View view, String searchType){
        mView = view;
        mSpHelper = new SpHelper(SEARCH_HISTORY+"_"+searchType);
        mHotList.add("热点1");
        mHotList.add("热点13");
        mHotList.add("热点12");
        mHotList.add("热点132");
        mHotList.add("热3点12");
        mHotList.add("热445点12");
        mHotList.add("热42342点12");
    }

//    service/user/search 用户搜索
//    service/strategy/searchlist 攻略搜索
//    service/map/search 地图搜索
//    service/video/searchlist 直播搜搜
    @Override
    public void subscribe(Object from){
        loadSearchHistory();
        loadHotKeys();
    }

    @Override
    public void unsubscribe(){
        mSpHelper.put(SEARCH_HISTORY_RAIDERS, mSearchList);
    }

    @Override
    public void searchByKey(String key){
        if(!mSearchList.contains(key)) {
            mSearchList.add(0, key);
        }else if(mSearchList.indexOf(key)>0) {
            //移动到第一个
            mSearchList.remove(key);
            mSearchList.add(0,key);
        }
        mView.showHisSearchList(mSearchList);
    }

    @Override
    public void clearHistory(){
        mSearchList.clear();
        mView.hideHisSearchList();
        //sp清空 unsubscribe会写入文件
    }

    @Override
    public void deleteHisListItem(int position){
        mSearchList.remove(position);
        if(mSearchList.isEmpty()) {
            mView.hideHisSearchList();
        }
        //sp删除 unsubscribe会写入文件
    }

    /**
     * 根据历史列表的不同数据结构 获取历史数据
     */
    @Override
    public void loadSearchHistory(){
        StringBuilder orign = new StringBuilder((String)mSpHelper.get(SEARCH_HISTORY_RAIDERS, mSearchList.toString()));
        if(orign.toString().contains("[]")) {
            mView.hideHisSearchList();
        }else {
            orign.deleteCharAt(orign.length()-1).deleteCharAt(0);
            String[] split = orign.toString().split(", ");
            mSearchList.addAll(Arrays.asList(split));//Arrays.asList(split)的集合 不可以增删
            mView.showHisSearchList(mSearchList);
        }
    }

    @Override
    public void loadHotKeys(){
        //获取网络 热词数据
        mView.showHotSearchKey(mHotList);
    }
}
