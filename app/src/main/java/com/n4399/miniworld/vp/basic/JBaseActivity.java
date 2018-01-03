package com.n4399.miniworld.vp.basic;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.widget.Toast;

import com.blueprint.LibApp;
import com.n4399.miniworld.R;

/**
 * @author 江祖赟.
 * @date 2017/6/6
 * @des [一句话描述]
 */
public class JBaseActivity extends AppCompatActivity {

    public Toast mDoubleFinish;

    @Override
    public void setContentView(@LayoutRes int layoutResID){
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
            getWindow().setEnterTransition(new Explode());
        }
        mDoubleFinish = Toast.makeText(this, LibApp.findString(R.string.jdouble_exit), Toast.LENGTH_SHORT);
    }

    public void doubleExit(){
        if(mDoubleFinish.getView() != null && mDoubleFinish.getView().isShown()) {
            finish();
        }else {
            mDoubleFinish.show();
        }
    }

}
