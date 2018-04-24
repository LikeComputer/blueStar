package me.tatarka.bindingcollectionadapter2.recv;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @another 江祖赟
 * @date 2018/4/18.
 */
public class JRecyclerView extends RecyclerView {
    public static RecyclerView.RecycledViewPool sRecycledViewPool = new RecycledViewPool();

    {
        setRecycledViewPool(sRecycledViewPool);
    }

    public JRecyclerView(Context context) {
        super(context);
    }

    public JRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public JRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
