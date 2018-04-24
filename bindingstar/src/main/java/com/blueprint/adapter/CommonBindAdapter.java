package com.blueprint.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.view.View;
import com.blueprint.R;
import com.blueprint.helper.CheckHelper;
import com.blueprint.widget.JFlowLayout;
import com.blueprint.widget.JRatingBar;
import java.util.List;

import static com.blueprint.widget.JToolbar.dp2px;

/**
 * @another 江祖赟
 * @date 2018/1/12.
 */
public class CommonBindAdapter {

    /**
     * 设置左/底部 的pading值
     * @param view
     * @param pading
     */
    @BindingAdapter("padinglb") public static void padingLB(View view, float pading) {
        view.setPadding(Math.round(pading), view.getPaddingTop(), view.getPaddingRight(), Math.round(pading));
    }

    /**
     * 设置右/底部 的pading值
     * @param view
     * @param pading
     */
    @BindingAdapter("padingrb") public static void padingRB(View view, float pading) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), Math.round(pading), Math.round(pading));
    }

    /**
     * 设置左/上/右 的pading值
     * @param view
     * @param padding
     */
    @BindingAdapter("padingltr") public static void setPadingltr(View view, float padding) {
        view.setPadding(Math.round(padding), Math.round(padding), Math.round(padding), view.getPaddingBottom());
    }


    @BindingAdapter("select") public static void setSelect(View view, boolean isSelected) {
        view.setSelected(isSelected);
    }


    //标签
    @BindingAdapter(value = { "keyList", "selectListener" }, requireAll = false)
    public static void setKeylist(JFlowLayout flowLayout, List<String> keys, JFlowLayout.OnItemSelectedListener selectedListener) {
        if (CheckHelper.safeLists(keys)) {
            flowLayout.removeAllViews();
            flowLayout.setHorizontalSpacing(dp2px(9))
                      .setVerticalSpacing(dp2px(9))
                      .setTextSize(13)
                      .setItemTvColor(Color.parseColor("#8b8b99"))
                      .setItemBackgroundResource(R.drawable.common_bg_flowitem)
                      .setOnItemSelectedListener(selectedListener)
                      .addContents(keys)
                      .setVisibility(View.VISIBLE);
        }
        else {
            flowLayout.setVisibility(View.GONE);
        }
    }


    //标签
    @BindingAdapter("rbProgress") public static void setRatingbarProgress(JRatingBar jRatingBar, float rating) {
        jRatingBar.setMark(rating);
    }

    /** !!! Binding conversion should be forbidden, otherwise it will conflict with
     *  {@code android:visiblity} attribute.
     */
    /*
    @BindingConversion
    public static int convertColorToString(int color) {
        switch (color) {
            case Color.RED:
                return R.string.red;
            case Color.WHITE:
                return R.string.white;
        }
        return R.string.app_name;
    }*/
}
