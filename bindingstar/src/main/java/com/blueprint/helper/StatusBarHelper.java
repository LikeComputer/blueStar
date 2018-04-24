package com.blueprint.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

import com.blueprint.LibApp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StatusBarHelper {

    public static void removeSystemUIFlag(Window window, int rmFlag) {
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        decorView.setSystemUiVisibility(systemUiVisibility & ~rmFlag);
    }


    public static int addFlag(int orignFlags, int addFlag) {
        return orignFlags |= addFlag;
    }


    public static int rmFlag(int orignFlags, int rmFlag) {
        return orignFlags &= ~rmFlag;
    }


    public static void addSystemUIFlag(Window window, int addFlag) {
        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | addFlag);
    }


    public static void addLayoutFullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addSystemUIFlag(activity.getWindow(), View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }


    /**
     * 全屏布局 状态栏透明，支持4.4以上版本
     * android:fitsSystemWindows="true" 设置DecorView的padingTop
     */
    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS|WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(colorId);
            window.setNavigationBarColor(Color.TRANSPARENT);
            //addSystemUIFlag(window, View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //}
            //else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //    Window window = activity.getWindow();
            //    window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    /**
     * 设置状态栏黑色字体图标，
     * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
     *
     * @return 1:MIUUI 2:Flyme 3:android6.0
     */
    public static int StatusBarLightMode(Activity activity) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (MIUISetStatusBarLightMode(activity.getWindow(), true)) {
                result = 1;
            } else if (FlymeSetStatusBarLightMode(activity.getWindow(), true)) {
                result = 2;
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                addSystemUIFlag(activity.getWindow(), View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                result = 3;
            }
        }
        return result;
    }


    /**
     * 6.0以上版本设置状态栏图标为深色和魅族特定的文字风格
     */
    public static void AndroidStatusBarTextMode(Window window, boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (dark) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                addSystemUIFlag(window, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                removeSystemUIFlag(window, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }


    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            AndroidStatusBarTextMode(window, dark);
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception ignored) {
            }
        }
        return result;
    }


    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     * {@link <a href="https://dev.mi.com/console/doc/detail?pId=1159"></a>}
     * 1.在新的 MIUI 版本（即基于 Android 6.0 ，开发版 7.7.13 及以后版本）：
     * 使用 View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR ，来设置「状态栏黑色字符」效果
     * 同时要设置 WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS，
     * 并且无 WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
     * 对于广大开发者而言，为了保证在新旧版本的 MIUI 都能实现「状态栏黑色字符」的效果，
     * 需要开发者同时写上以上两种实现方法。
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    private static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                //if (dark) {
                //    //miui9
                //    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //    addSystemUIFlag(window, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                //}
                AndroidStatusBarTextMode(window, dark);
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                extraFlagField.invoke(window, dark ? darkModeFlag : 0, darkModeFlag);
                result = true;
            } catch (Exception ignored) {
                result = false;
            }
        }
        return result;
    }

    /**
     * 一般用于 布局填充屏幕的情况下，适配低版本布局fitSystemWindow属性 设置padingTop的问题
     * @return
     */
    public static int getStatusBarHeight4Pading() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return 0;
        } else {
            return getStatusBarHeight();
        }
    }

    public static int getStatusBarHeight() {
        return getSystemResPixelSize("status_bar_height");
    }

    public static int getSystemResPixelSize(String status_bar_height) {
        Resources system = Resources.getSystem();
        int resourceId = system.getIdentifier(status_bar_height, "dimen", "android");
        return system.getDimensionPixelSize(resourceId);
    }


    /**
     * 获取导航栏高度
     * <p>0代表不存在</p>
     *
     * @return 导航栏高度
     */
    public static int getNavBarHeight() {
        boolean hasMenuKey = ViewConfiguration.get(LibApp.getContext()).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        if (!hasMenuKey && !hasBackKey) {
            return getSystemResPixelSize("navigation_bar_height");
        } else {
            return 0;
        }
    }


    /**
     * 获取 actionBar的高度
     *
     * @return
     */
    public static float getActionBarHeight() {
        TypedArray actionbarSizeTypedArray = LibApp.getContext().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        float dimension = actionbarSizeTypedArray.getDimension(0, 0);
        actionbarSizeTypedArray.recycle();
        return dimension;
    }


    /**
     * 隐藏导航栏
     *
     * @param activity activity
     */
    public static void hideNavBar(@NonNull final Activity activity) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        if (getNavBarHeight() > 0) {
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            addSystemUIFlag(activity.getWindow(), uiOptions);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // notification bar
    ///////////////////////////////////////////////////////////////////////////


    /**
     * 显示通知栏
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.EXPAND_STATUS_BAR"/>}</p>
     *
     * @param isSettingPanel {@code true}: 打开设置<br>{@code false}: 打开通知
     */
    public static void showNotificationBar(final boolean isSettingPanel) {
        String methodName = (Build.VERSION.SDK_INT <= 16) ? "expand" : (isSettingPanel ? "expandSettingsPanel" : "expandNotificationsPanel");
        invokePanels(methodName);
    }


    /**
     * 隐藏通知栏
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.EXPAND_STATUS_BAR"/>}</p>
     */
    public static void hideNotificationBar() {
        String methodName = (Build.VERSION.SDK_INT <= 16) ? "collapse" : "collapsePanels";
        invokePanels(methodName);
    }


    /**
     * 反射唤醒通知栏
     *
     * @param methodName 方法名
     */
    private static void invokePanels(final String methodName) {
        try {
            @SuppressLint("WrongConstant") Object service = LibApp.getContext().getSystemService("statusbar");
            Class<?> statusBarManager = Class.forName("android.app.StatusBarManager");
            Method expand = statusBarManager.getMethod(methodName);
            expand.invoke(service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
