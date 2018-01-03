package com.n4399.miniworld.vp.raiders.synthesis.sort;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.blueprint.LibApp;
import com.blueprint.adapter.frgmt.BaseFrgmtFractory;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JBaseTabVpActivity;

import april.yun.other.JTabStyleDelegate;
import butterknife.BindArray;

import static com.blueprint.Consistent.DIFF_INDEX;
import static com.blueprint.Consistent.DIFF_TYPE;

/**
 * 物品百科/图鉴大全分类
 */
public class SynthesDetailActivy extends JBaseTabVpActivity {

    public static final String 图鉴大全 = "pokedex";//直接用于接口字段
    public static final String 物品百科 = "goods";
    private String mDetailType = 图鉴大全;
    private int mIndex;
    @BindArray(R.array.raider_synth_tjdq) String[] mTjda_titles;
    @BindArray(R.array.raider_synth_wpbk) String[] mWpbk_titles;

    public static void startActivity(Activity activity, String type, int index){
        Intent intent = new Intent(activity, SynthesDetailActivy.class);
        intent.putExtra(DIFF_TYPE, type);
        intent.putExtra(DIFF_INDEX, index);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        Intent intent = getIntent();
        if(intent != null) {
            //类型 关系到标题 在viewpager需要使用
            mDetailType = intent.getStringExtra(DIFF_TYPE);
            mIndex = intent.getIntExtra(DIFF_INDEX, mIndex);
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
                    fragment = SynthesDetailFrgmt.getInstance(mDetailType, position);
                    fmCache.put(position, fragment);
                }
                return fragment;
            }

        };
    }

    @Override
    protected String[] setTabTitles(){
        if(图鉴大全.equals(mDetailType)) {
            return mTjda_titles;
        }else {
            return mWpbk_titles;
        }
    }

    @Override
    protected void reConfigTabStrip(JTabStyleDelegate tabStyleDelegate){
        super.reConfigTabStrip(tabStyleDelegate);
        tabStyleDelegate.setShouldExpand(true);
    }

    @Override
    protected String setTitle(){
        return getTitle(mDetailType);
    }

    private String getTitle(String type){
        if(图鉴大全.equals(mDetailType)) {
            return LibApp.findString(R.string.fm_radiers_syn_tjdq);
        }else {
            return LibApp.findString(R.string.fm_radiers_syn_wpbk);
        }
    }

}
