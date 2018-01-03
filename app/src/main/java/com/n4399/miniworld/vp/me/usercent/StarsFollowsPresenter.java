package com.n4399.miniworld.vp.me.usercent;

import com.blueprint.basic.common.GeneralListPresenter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class StarsFollowsPresenter extends GeneralListPresenter implements StarsFollowsContract.Presenter {

    StarsFollowsContract.View mView;

    public StarsFollowsPresenter(StarsFollowsContract.View view){
        super(view);
        mView = view;
    }
}