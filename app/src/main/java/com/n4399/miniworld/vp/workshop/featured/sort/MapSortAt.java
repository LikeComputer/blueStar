package com.n4399.miniworld.vp.workshop.featured.sort;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.blueprint.LibApp;
import com.blueprint.adapter.frgmt.BaseFrgmtFractory;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.WSfeature;
import com.n4399.miniworld.vp.basic.JBaseTabVpActivity;

import java.util.ArrayList;

import april.yun.other.JTabStyleDelegate;

import static com.blueprint.Consistent.DIFF_INDEX;
import static com.blueprint.Consistent.DIFF_TYPE;

/**
 * 物品百科/图鉴大全分类
 */
public class MapSortAt extends JBaseTabVpActivity {

    private int mIndex;
    String[] mTitles;
    private ArrayList<WSfeature.TypelistBean> mSortsList;

    public static void startActivity(Activity activity, ArrayList<WSfeature.TypelistBean> sorts, int index){
        Intent intent = new Intent(activity, MapSortAt.class);
        intent.putParcelableArrayListExtra(DIFF_TYPE, sorts);
        intent.putExtra(DIFF_INDEX, index);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        Intent intent = getIntent();
        if(intent != null) {
            //类型 关系到标题 在viewpager需要使用
            mSortsList = intent.getParcelableArrayListExtra(DIFF_TYPE);
            mIndex = intent.getIntExtra(DIFF_INDEX, mIndex);
            mTitles = new String[mSortsList.size()];
            for(int i = 0; i<mSortsList.size(); i++) {
                mTitles[i] = mSortsList.get(i).getTitle();
            }
        }
        super.onCreate(savedInstanceState);
        mBaseViewpager.setCurrentItem(mIndex);
    }

    @Override
    public void onContentChanged(){
        super.onContentChanged();
    }


    @Override
    protected BaseFrgmtFractory setFrgmtProvider(){
        return new BaseFrgmtFractory() {
            @Override
            public Fragment createFragment(int position){
                Fragment fragment = fmCache.get(position);
                if(fragment == null) {
                    fragment = MapTypeDetailFrgmt.getInstance(mSortsList.get(position).getId());
                    fmCache.put(position, fragment);
                }
                return fragment;
            }

        };
    }

    @Override
    protected String[] setTabTitles(){
        return mTitles;
    }

    @Override
    protected void reConfigTabStrip(JTabStyleDelegate tabStyleDelegate){
        super.reConfigTabStrip(tabStyleDelegate);
        tabStyleDelegate.setShouldExpand(true);
    }

    @Override
    protected String setTitle(){
        return LibApp.findString(R.string.frgmt_ws_feature_mapsort_title);
    }
}
