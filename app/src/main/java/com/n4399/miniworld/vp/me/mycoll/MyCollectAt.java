package com.n4399.miniworld.vp.me.mycoll;

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
import com.n4399.miniworld.data.bean.AccountBean;
import com.n4399.miniworld.vp.basic.JBaseTabVpActivity;

import april.yun.other.JTabStyleDelegate;
import butterknife.BindArray;
import butterknife.ButterKnife;

/**
 * @another [https://github.com/ZuYun]
 * @desc [我的收藏]
 */
public class MyCollectAt extends JBaseTabVpActivity {

    @BindArray(R.array.me_my_collection) String[] mTitles;
    private AccountBean mAccount;

    public static void start(Activity activity, AccountBean account){
        Intent intent = new Intent(activity, MyCollectAt.class);
        intent.putExtra(Consistent.BUND_TAG, account);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        mAccount = getIntent().getParcelableExtra(Consistent.BUND_TAG);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected String setTitle(){
        return LibApp.findString(R.string.me_my_collection);
    }

    @Override
    protected BaseFrgmtFractory setFrgmtProvider(){
        return new BaseFrgmtFractory() {
            private static final int GAMEMAP = 0;
            private static final int RAIDER = 1;
            private static final int VIDEO = 2;

            @Override
            public Fragment createFragment(int position){
                Fragment fragment = fmCache.get(position);
                if(fragment == null) {
                    switch(position) {
                        case GAMEMAP:
                            fragment = MyRaidersCollectFrgmt.getInstance();
                            break;
                        case RAIDER:
                            fragment = MyRaidersCollectFrgmt.getInstance();
                            break;
                        case VIDEO:
                            fragment = MyVideoCollectFrgmt.getInstance();
                            break;
                    }
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
        tabStyleDelegate.setShouldExpand(true);
    }
}