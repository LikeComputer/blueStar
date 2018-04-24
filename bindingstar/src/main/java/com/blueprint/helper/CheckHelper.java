package com.blueprint.helper;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.blueprint.Consistent;
import com.blueprint.LibApp;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static com.blueprint.Consistent.DEFAULTSTR;
import static com.blueprint.helper.PackageHelper.getPackageName;

public class CheckHelper {

    public static final int EQUALTAG = 0x7f199101;
    // 17 位加权因子
    private static final int[] RATIO_ARR = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 校验码列表
    private static final char[] CHECK_CODE_LIST = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    private static final int NUM_0 = '0';

    private static final int ID_LENGTH = 17;
    private static long sLastClickTime;

    public static boolean verifyId18(String idNo) {
        if (idNo == null || idNo.isEmpty()) {
            return false;
        }
        idNo = idNo.trim();
        if (idNo.length() != 18) {
            return false;
        }
        // 获取身份证号字符数组
        char[] idCharArr = idNo.toCharArray();
        // 获取最后一位（身份证校验码）
        char verifyCode = idCharArr[ID_LENGTH];
        // 身份证号第1-17加权和
        int idSum = 0;
        // 余数
        int residue;

        for (int i = 0; i < ID_LENGTH; i++) {
            int value = idCharArr[i] - NUM_0;
            idSum += value * RATIO_ARR[i];
        }
        // 取得余数   为什么要 mod11
        //https://www.zhihu.com/question/20205184
        residue = idSum % 11;

        return Character.toUpperCase(verifyCode) == CHECK_CODE_LIST[residue];
    }


    /**
     * 检查对象是否相同/都不为空
     *
     * @return true 安全的对象 都不为空
     */
    public static boolean isEqual(Object object1, Object object2) {
        if (safeObjects(object1, object2)) {
            if (object2.equals(object1)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检查List集合是否 有效
     *
     * @return true 安全的Collection 都不为空
     */
    public static boolean safeLists(Collection... lists) {
        for (Collection list : lists) {
            if (list == null || list.size() <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return true 所有字符串都有效
     */
    public static boolean safeStrings(CharSequence... strs) {
        for (CharSequence str : strs) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
        }
        return true;
    }


    /**
     * @return true 所有字符串都有效
     */
    public static boolean safeObjectStr(Object... strs) {
        if (strs != null) {
            for (Object str : strs) {
                if (str == null || TextUtils.isEmpty(str.toString())) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }


    /**
     * 检查对象是否为空
     *
     * @return true 安全的对象 都不为空
     */
    public static boolean safeObjects(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }


    /**
     * 注意传过来的对象 是不相关的not like{p,p.friend}
     */
    public static <T> boolean safeArrays(T[]... arrays) {
        if (arrays != null) {
            for (Object[] array : arrays) {
                if (array == null || array.length == 0) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }


    public static <T> T checkNotNull(T o) {
        return checkNotNull(o, "CheckHelper");
    }


    public static <T> T checkNotNull(T o, String warm) {
        if (o == null) {
            throw new NullPointerException(warm);
        }
        return o;
    }

    public static Object safeObject(Object o) {
        if (o == null) {
            return "";
        } else {
            return o;
        }
    }

    /**
     * 返回 安全的字符串
     *
     * @return 为空则返回“”
     */
    public static String safeString(Object str) {
        if (str != null) {
            return str.toString().trim();
        } else {
            return DEFAULTSTR;
        }
    }


    public static boolean viewTagBoolean(View view, int viewTag) {
        if (view.getTag(viewTag) != null && view.getTag(viewTag) instanceof Boolean) {
            return (boolean) view.getTag(viewTag);
        } else {
            return false;
        }
    }


    public static Long viewTagLong(View view, int viewTag) {
        if (view.getTag(viewTag) != null && view.getTag(viewTag) instanceof Long) {
            return (Long) view.getTag(viewTag);
        } else {
            return 0L;
        }
    }

    public static int viewTagInt(View view, int viewTag) {
        if (view.getTag(viewTag) != null && view.getTag(viewTag) instanceof Integer) {
            return (int) view.getTag(viewTag);
        } else {
            return 0;
        }
    }


    /**
     * 到底有没有某项权限，怎么检测呢，基于以往 Android 在这方面的不精细，
     * 很多人都不会太在意这方面的逻辑判断，新出的6.0系统也只是基于targetSdkVersion 23以上的app的判断，
     * 包括6.0以下的版本，怎样判断是不是被安全中心这种禁掉了呢，这就需要 AppOpsManager 这个类了
     * AppOpsManager.OPSTR_CAMERA
     * <li>
     * <p>AppOpsManager.MODE_ALLOWED 有权限</p>
     * <p>AppOpsManager.MODE_IGNORED "被禁止了</p>
     * <p>AppOpsManager.MODE_ERRORED 出错了</p>
     * </li>
     */
    public static int checkPermission(final Activity activity, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AppOpsManager appOpsManager = (AppOpsManager) LibApp.getContext().getSystemService(Context.APP_OPS_SERVICE);
            int checkResult = appOpsManager.checkOpNoThrow(permission, Binder.getCallingUid(), getPackageName());
            if (checkResult == AppOpsManager.MODE_ALLOWED) {
                LogHelper.Log_d("有权限");
            } else if (checkResult == AppOpsManager.MODE_IGNORED) {
                // TODO: 只需要依此方法判断退出就可以了，这时是没有权限的。
                LogHelper.Log_d("权限被禁止了");
                //                new AlertDialog.Builder(activity).setTitle("权限")
                //                        .setCancelable(true).setMessage("权限被禁止，需要进入应用设置页面开启权限")
                //                        .setPositiveButton("去修改", new DialogInterface.OnClickListener() {
                //                            @Override
                //                            public void onClick(DialogInterface dialog, int which){
                //                                    IntentHelper.toAppDetailPage(activity,LibApp.getPackageName());
                //                            }
                //                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                //                            @Override
                //                            public void onClick(DialogInterface dialog, int which){
                //                                dialog.dismiss();
                //                            }
                //                        }).create().show();
            } else if (checkResult == AppOpsManager.MODE_ERRORED) {
                LogHelper.Log_d("出错了");
            } else if (checkResult == 4) {
                LogHelper.Log_d("权限需要询问");
            }
            return checkResult;
        }
        return AppOpsManager.MODE_ALLOWED;
    }


    /**
     * 判断是否包含
     * <p>( window.getDecorView().getSystemUiVisibility()&View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN ) == View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;</p>
     */
    public static boolean checkHasFlag(int orin, int flag) {
        return (orin & flag) == flag;
    }


    /**
     * 判断App是否是Debug版本
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isAppDebug() {
        try {
            PackageManager pm = LibApp.getContext().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(LibApp.getContext().getPackageName(), 0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 防抖处理
     *
     * @param interval 毫秒
     * @return 在间隔interval时间 返回false
     */
    public static boolean throttleFirst(int interval) {
        long currentClickTime = System.nanoTime();
        if (TimeUnit.NANOSECONDS.toMillis(currentClickTime - sLastClickTime) > interval) {
            sLastClickTime = currentClickTime;
            return true;
        }
        return false;
    }


    /**
     * 防抖处理
     * 类似throttleFirst
     *
     * @param interval 毫秒
     * @return 在间隔interval时间 返回false
     */
    public static boolean throttleFirst(@NonNull View view, int interval) {
        Object tag = view.getTag(Consistent.ViewTag.click_time);
        long lastClickTime = 0;
        if (tag != null) {
            lastClickTime = (long) tag;
        }
        long currentClickTime = System.nanoTime();
        if (TimeUnit.NANOSECONDS.toMillis(currentClickTime - lastClickTime) > interval) {
            view.setTag(Consistent.ViewTag.click_time, currentClickTime);
            return true;
        }
        return false;
    }


    /**
     * 连续防抖处理
     * 连续点击无数次(interv) 只返回一次true
     *
     * @param interval 毫秒
     * @return 在间隔interval时间 返回false
     */
    public static boolean throttleFirstContinuous(int interval) {
        long currentClickTime = System.nanoTime();
        if (TimeUnit.NANOSECONDS.toMillis(currentClickTime - sLastClickTime) > interval) {
            sLastClickTime = currentClickTime;
            return true;
        }
        sLastClickTime = currentClickTime;
        return false;
    }


    /**
     * 连续防抖处理
     * 连续点击无数次(interv) 只返回一次true
     *
     * @param interval 毫秒
     * @return 在间隔interval时间 返回false
     */
    public static boolean throttleFirstContinuous(@NonNull View view, int interval) {
        Object tag = view.getTag(Consistent.ViewTag.click_time);
        long lastClickTime = 0;
        if (tag != null) {
            lastClickTime = (long) tag;
        }
        long currentClickTime = System.nanoTime();
        view.setTag(Consistent.ViewTag.click_time, currentClickTime);
        if (TimeUnit.NANOSECONDS.toMillis(currentClickTime - lastClickTime) > interval) {
            return true;
        }
        return false;
    }
}

