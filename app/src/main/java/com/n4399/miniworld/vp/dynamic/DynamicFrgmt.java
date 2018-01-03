package com.n4399.miniworld.vp.dynamic;


import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.blueprint.basic.JBasePresenter;
import com.n4399.miniworld.vp.basic.JBaseTitleFrgmt;


/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [动态]
 */
public class DynamicFrgmt extends JBaseTitleFrgmt {
    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout container){

    }

    @Override
    protected JBasePresenter initPresenter(){
        return null;
    }

    @Override
    protected String setTitle(){
        return null;
    }
}
