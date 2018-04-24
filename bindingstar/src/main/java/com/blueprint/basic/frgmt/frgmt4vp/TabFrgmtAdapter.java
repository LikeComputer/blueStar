package com.blueprint.basic.frgmt.frgmt4vp;

import android.support.annotation.ArrayRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import me.tatarka.bindingcollectionadapter2.Utils;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [一句话描述]
 */
public class TabFrgmtAdapter extends FragmentStatePagerAdapter {
    private String[] mTabtiles;
    private BaseFrgmtFractory mFrmtFractory;


    public TabFrgmtAdapter(FragmentManager fm, @ArrayRes int tabtilesArrayIds, BaseFrgmtFractory frmtFractory) {
        this(fm, Utils.getContext().getResources().getStringArray(tabtilesArrayIds), frmtFractory);
    }


    public TabFrgmtAdapter(FragmentManager fm, String[] tabtiles, BaseFrgmtFractory frmtFractory) {
        super(fm);
        mTabtiles = tabtiles.clone();
        mFrmtFractory = frmtFractory;
    }


    @Override public Fragment getItem(int position) {
        return mFrmtFractory.privodeFrgmt(position);
    }


    @Override public int getCount() {
        return mTabtiles.length;
    }


    @Override public CharSequence getPageTitle(int position) {
        return mTabtiles[position];
    }


    public String[] getTabtiles() {
        return mTabtiles;
    }
}
