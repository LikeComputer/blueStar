package com.n4399.miniworld.vp.me.search;

import com.n4399.miniworld.vp.jpublic.SearchContract;
import com.n4399.miniworld.vp.jpublic.SearchPresenter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class GuysSearchPresenter extends SearchPresenter<String> {

    public GuysSearchPresenter(SearchContract.View view, String searchType){
        super(view, searchType);
    }

    @Override
    public void loadHotKeys(){
        mView.showHotSearchKey(null);
    }
}