package com.n4399.miniworld.vp.workshop.online.seach;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.blueprint.LibApp;
import com.n4399.miniworld.Constants;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.RoomBean;
import com.n4399.miniworld.vp.jpublic.BaseSearchAt;
import com.n4399.miniworld.vp.jpublic.SearchPresenter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class RoomSearchAt extends BaseSearchAt<RoomBean> {

    RoomSearchPresenter mPresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, RoomSearchAt.class);
        //intent.putExtra(SEARCH_TYPE, search_type);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected SearchPresenter initSearchListPresenter(){
        return mPresenter = new RoomSearchPresenter(this, Constants.SearchData.SEARCH_ROOM);
    }

    @Override
    protected Fragment replaceResultFrgmt(){
        return RoomSearchResultFrgmt.getInstance();
    }

    @Override
    protected CharSequence setSearchHint(){
        return LibApp.findString(R.string.frgmt_ws_online_search_hint);
    }
}