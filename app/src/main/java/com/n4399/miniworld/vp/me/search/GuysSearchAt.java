package com.n4399.miniworld.vp.me.search;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.blueprint.LibApp;
import com.n4399.miniworld.Constants;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.jpublic.BaseSearchAt;
import com.n4399.miniworld.vp.jpublic.SearchPresenter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [找人]
 */
public class GuysSearchAt extends BaseSearchAt<String> {

    private GuysSearchPresenter mPresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, GuysSearchAt.class);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }


    @Override
    protected SearchPresenter initSearchListPresenter(){
        return new GuysSearchPresenter(this, Constants.SearchData.SEARCH_USER);
    }

    @Override
    protected Fragment replaceResultFrgmt(){
        return GuysSearchResultFrgmt.getInstance();
    }

    @Override
    protected CharSequence setSearchHint(){
        return LibApp.findString(R.string.me_center_find_user);
    }
}