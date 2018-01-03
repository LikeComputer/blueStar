package com.n4399.miniworld.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.n4399.miniworld.R;

/**
 * @another 江祖赟
 * @date 2017/6/20.
 */
@SuppressLint("AppCompatCustomView")
public class LoadingDataView extends ImageView {
    public LoadingDataView(Context context){
        super(context);
    }

    public LoadingDataView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    public LoadingDataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        setBackgroundResource(R.drawable.loading_ani);
        ( (AnimationDrawable)getBackground() ).start();
    }
}
