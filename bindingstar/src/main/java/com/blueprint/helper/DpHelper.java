package com.blueprint.helper;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.blueprint.LibApp;

public class DpHelper {

    private DpHelper() {
        throw new AssertionError();
    }

    public static float dp2px(float px) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, dm);
    }
    public static float sp2px(float px) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, px, dm);
    }

    /**
     * dip转为PX
     */
    public static int dp2px2(float dipValue) {
        float fontScale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * fontScale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 px 的单位 转成为 dp
     */
    public static int px2dp2(float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕的宽度px
     * @return 屏幕宽px
     */
    public static int getScreenWidth() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        //WindowManager windowManager = (WindowManager) LibApp.getContext().getSystemService(Context.WINDOW_SERVICE);
        //DisplayMetrics displayMetrics = new DisplayMetrics();
        //windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度px
     *
     * @return 屏幕高px
     */
    public static int getScreenHeight() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        //WindowManager windowManager = (WindowManager) LibApp.getContext().getSystemService(Context.WINDOW_SERVICE);
        //DisplayMetrics displayMetrics = new DisplayMetrics();
        //windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getStatusBarHeight() {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = LibApp.getContext().getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
