package com.n4399.miniworld.vp.raiders.experience;

import java.util.ArrayList;
import java.util.List;

/**
 * @another 江祖赟
 * @date 2017/6/21.
 */
public class ExperiencePresenter implements ExperienceContract.Presenter {

    private List mTextRaidersData = new ArrayList();
    private List mVideoRaidersData = new ArrayList();
    private List mMapSeedData = new ArrayList();
    private List mShowListData = new ArrayList();

    ExperienceContract.View mView;

    public ExperiencePresenter(ExperienceContract.View view){
        mView = view;
    }

    @Override
    public void subscribe(Object from){

    }

    @Override
    public void unsubscribe(){

    }

    @Override
    public void loadTextRaiders(){
        mView.showTextRaiders(mShowListData = mTextRaidersData);
    }

    @Override
    public void loadVideoRaiders(){
        mView.showVideoRaiders(mShowListData = mVideoRaidersData);
    }

    @Override
    public void loadMapSeed(){
        mView.showMapSeed(mShowListData = mMapSeedData);
    }

    @Override
    public void up2LoadMore(){
        mView.onMoreLoad(mShowListData);
    }
}
