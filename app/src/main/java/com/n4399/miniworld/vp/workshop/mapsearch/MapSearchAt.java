package com.n4399.miniworld.vp.workshop.mapsearch;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blueprint.LibApp;
import com.blueprint.widget.JEditText;
import com.n4399.miniworld.Constants;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.vp.jpublic.BaseSearchAt;
import com.n4399.miniworld.vp.jpublic.SearchPresenter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MapSearchAt extends BaseSearchAt<HotMapBean> implements AdapterView.OnItemSelectedListener {

    private MapSearchPresenter mPresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, MapSearchAt.class);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected SearchPresenter initSearchListPresenter(){
        return mPresenter = new MapSearchPresenter(this, Constants.SearchData.SEARCH_MAP);
    }

    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout container){
        super.onCreateContent(inflater, container);
        LinearLayout titleSearchBar = (LinearLayout)actRaiderSearchEtKey.getParent();
        titleSearchBar.removeAllViews();
        inflater.inflate(R.layout.frgmt_workshop_mapsearch, titleSearchBar);
        Spinner frgmtWshorpFeatureSortSpan = (Spinner)titleSearchBar.findViewById(R.id.frgmt_wshorp_feature_sort_span);
        actRaiderSearchEtKey = (JEditText)titleSearchBar.findViewById(R.id.act_raider_search_et_key);
        frgmtWshorpFeatureSortSpan.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.frgmt_workshop_mapsearch_type, R.layout.simple_item_list_tv));
        frgmtWshorpFeatureSortSpan.setOnItemSelectedListener(this);
        configViews();
    }

    @Override
    protected Fragment replaceResultFrgmt(){
        return MapSearchResultFrgmt.getInstance();
    }

    @Override
    protected CharSequence setSearchHint(){
        return LibApp.findString(R.string.frgmt_ws_tv_map_search);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        ( (TextView)view ).setTextColor(LibApp.findColor(R.color.black333));
        Toast.makeText(this, ""+( (TextView)view ).getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){

    }
}