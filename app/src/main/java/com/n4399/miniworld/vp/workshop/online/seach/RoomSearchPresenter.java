package com.n4399.miniworld.vp.workshop.online.seach;

import com.n4399.miniworld.vp.jpublic.SearchContract;
import com.n4399.miniworld.vp.jpublic.SearchPresenter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class RoomSearchPresenter extends SearchPresenter {

    public RoomSearchPresenter(SearchContract.View view, String searchType){
        super(view, searchType);
    }

    @Override
    public void loadHotKeys(){
        mView.showHotSearchKey(null);
    }

//    @Override
//    public void loadSearchHistory(){
//        mView.showHisSearchList(null);
//
//    }
}