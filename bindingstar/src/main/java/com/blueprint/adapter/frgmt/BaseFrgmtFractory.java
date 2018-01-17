package com.blueprint.adapter.frgmt;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [一句话描述]
 */
public abstract class BaseFrgmtFractory {
    protected SparseArray<Fragment> mFrgmtCache = new SparseArray<Fragment>();

    {
        initFrgment(mFrgmtCache);
    }


    protected void initFrgment(SparseArray<Fragment> frgmtCache) {
    }


    protected final Fragment privodeFrgmt(int position) {
        Fragment fragment = getFrgmt(position);
        if(fragment == null) {
            fragment = createFragment(position);
            putFrgmt(position, fragment);
        }
        return fragment;
    }


    protected Fragment createFragment(int position) {
        return mFrgmtCache.get(position);
    }


    protected Fragment getFrgmt(int position) {
        return mFrgmtCache.get(position);
    }


    protected void putFrgmt(int key, Fragment frgmt) {
        mFrgmtCache.put(key, frgmt);
    }


    public SparseArray<Fragment> getFrgmtCache() {
        return mFrgmtCache;
    }
}
