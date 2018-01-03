package com.n4399.miniworld.vp.jpublic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.blueprint.LibApp;
import com.blueprint.rx.RxBus;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.event.SearchEvent;
import com.n4399.miniworld.vp.live.search.LiveSearchFrgmt;
import com.n4399.miniworld.vp.raiders.search.SearchResultFrgmt;

import static com.n4399.miniworld.Constants.RaidersKey.FM_SEARCHRESULT;
import static com.n4399.miniworld.Constants.SearchData.SEARCH_RAIDERS;

/**
 * 直播/攻略 搜索
 */
public class SearchActivity extends BaseSearchAt<String> {

    private String mSearch_type;
    public static final String SEARCH_TYPE = "search_type";

    public static void start(Activity activity, String search_type){
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra(SEARCH_TYPE, search_type);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        if(getIntent() != null) {
            mSearch_type = getIntent().getStringExtra(SEARCH_TYPE);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected SearchPresenter initSearchListPresenter(){
        return new SearchPresenter<String>(this, mSearch_type);
    }

    @Override
    protected CharSequence setSearchHint(){
        if(SEARCH_RAIDERS.equals(mSearch_type)) {
            return LibApp.findString(R.string.raiders_tb_tv_search);
        }else {
            return LibApp.findString(R.string.frgmt_live_title);
        }
    }

    @Override
    protected Fragment replaceResultFrgmt(){
        if(SEARCH_RAIDERS.equals(mSearch_type)) {
            return SearchResultFrgmt.getInstance(FM_SEARCHRESULT);
        }else {
            //直播
            return LiveSearchFrgmt.getInstance();
        }
    }

    @Override
    public void actSearch(String key){
        //通过rxbus通知fragment刷新数据
        RxBus.getInstance().post(new SearchEvent(key));
    }
}
