package com.n4399.miniworld.vp.live.sort;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.blueprint.Consistent;
import com.blueprint.LibApp;
import com.blueprint.adapter.frgmt.BaseFrgmtFractory;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JBaseTabVpActivity;

/**
 * @another 江祖赟
 * @date 2017/6/29.
 */
public class RecoSortAt extends JBaseTabVpActivity {

    private String[] mTitles = new String[4];

    public static void start(Activity activity,int index){
        Intent intent = new Intent(activity, RecoSortAt.class);
        intent.putExtra(Consistent.BUND_TAG, index);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        mTitles[0] = LibApp.findString(R.string.live_reco_xxyl);
        mTitles[1] = LibApp.findString(R.string.live_reco_dljx);
        mTitles[2] = LibApp.findString(R.string.live_reco_scsk);
        mTitles[3] = LibApp.findString(R.string.live_reco_jzjx);
        int index = getIntent().getIntExtra(Consistent.BUND_TAG, 0);
        super.onCreate(savedInstanceState);
        mBaseViewpager.setCurrentItem(index);
    }

    @Override
    protected BaseFrgmtFractory setFrgmtProvider(){

        return new BaseFrgmtFractory() {
            @Override
            public Fragment createFragment(int position){
                Fragment fragment = fmCache.get(position);
                if(fragment == null) {
                    fragment = RecoSortDetailFrgmt.getInstance(position+1);
                }
                return fragment;
            }

        };
    }

    @Override
    protected String[] setTabTitles(){
        return mTitles;
    }
}
