package com.yun.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import com.blueprint.LibApp;
import com.blueprint.helper.CheckHelper;
import com.blueprint.helper.RegexHelper;
import com.blueprint.helper.ToastHelper;
import com.jakewharton.rxbinding2.view.RxView;
import com.yun.juimodel.R;
import com.yun.juimodel.data.commbean.IRoute;
import io.reactivex.functions.Consumer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static android.text.format.DateUtils.SECOND_IN_MILLIS;
import static me.tatarka.bindingcollectionadapter2.Utils.LOG;

/**
 * @another 江祖赟
 * @date 2017/7/4.
 */
public class Data2UIhelper {

    public static String AVATAR_TIME = refreshAvatarTime();


    public static String getMiddlePortrait(String uid) {
        String avtor = "http://a.img4399.com/" + (uid == null ? "0" : uid) + "/middle?" + getTimeStamp();
        return avtor;
    }


    public static String formartTime(Object time) {
        if(time != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(new Date(Long.parseLong(time.toString()) * 1000));
        } else {
            return "--";
        }
    }


    private static String getTimeStamp() {
        return AVATAR_TIME;
    }


    public static String refreshAvatarTime() {
        return AVATAR_TIME = String.valueOf(new Date().getTime());
    }


    public static List<String> getImgPaths(List<? extends IRoute> imageItems) {
        List<String> imagePaths = new ArrayList<>();
        for (IRoute imageItem : imageItems) {
            String path = imageItem.pic;
            imagePaths.add(path);
        }
        return imagePaths;
    }


    public static void rxClick(View view, Consumer observer) {
        RxView.clicks(view).throttleFirst(600, TimeUnit.MILLISECONDS).subscribe(observer);
    }


    /**
     * 计算与当前的时间差相差多少时间
     * strTimestamp单位秒
     */
    public static String dateDistance2(String strTimestamp) {
        if(TextUtils.isEmpty(strTimestamp) || strTimestamp.equals("null") && !RegexHelper.isNumeric(strTimestamp)) {
            return "时间错误：" + strTimestamp;
        }
        long timestamp = Long.parseLong(strTimestamp) * 1000;
        long timeLong = System.currentTimeMillis() - timestamp;
        if(timeLong < 60 * 1000) {
            return "刚刚";
        } else if(timeLong < 60 * 60 * 24 * 1000 * 3) {
            return DateUtils.getRelativeTimeSpanString(timestamp, System.currentTimeMillis(), SECOND_IN_MILLIS).toString();
        } else {
            Time time = new Time();
            time.set(timestamp);
            Time ctime = new Time();
            ctime.set(System.currentTimeMillis());
            if(time.year == ctime.year) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
                return sdf.format(new Date(timestamp));
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(new Date(timestamp));
            }
        }
    }

    //@NonNull public static WebShareEntity getWebShareEntity(String mShareurl) {
    //    WebShareEntity webShareEntity = new WebShareEntity();
    //    webShareEntity.setShareText("标题");
    //    UMWeb umWeb = new UMWeb(mShareurl);
    //    umWeb.setTitle("4399迷你世界助手");
    //    umWeb.setThumb(new UMImage(JApp.getContext(), R.drawable.ic_back));
    //    umWeb.setDescription(JApp.findString(R.string.me_share_app_desc));
    //    webShareEntity.setUmWeb(umWeb);
    //    return webShareEntity;
    //}


    public static SpannableString getSpanModify(SpannableString orign, String keys, int kstyleRes) {
        Context context = LibApp.getContext();
        try {
            //关键字高亮
            Pattern p = Pattern.compile(keys, Pattern.CASE_INSENSITIVE);//启用不区分大小写的匹配。
            Matcher m = p.matcher(orign);
            while (m.find()) {
                orign.setSpan(new TextAppearanceSpan(context, kstyleRes), m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } catch (PatternSyntaxException e) {
            LOG(Log.getStackTraceString(e));
        }
        return orign;
    }


    public static SpannableString hightLightStrParser(@NonNull SpannableString orign, String key, int keyColor) {
        if(CheckHelper.safeStrings(orign, key)) {
            try {
                Pattern p = Pattern.compile(key);
                Matcher m = p.matcher(orign);
                while (m.find()) {
                    int start = m.start();
                    int end = m.end();
                    if(!TextUtils.isEmpty(m.group())) {
                        orign.setSpan(new ForegroundColorSpan(keyColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            } catch (PatternSyntaxException e) {
                LOG(Log.getStackTraceString(e));
            }
        }
        return orign;
    }


    public static CharSequence getZScore(float zscore) {
        String score = String.valueOf(zscore);
        return getSpanModify(new SpannableString(LibApp.findString(R.string.home_item_hotmap_score, score)), score,
                R.style.game_msg_zscore);
    }


    public static void showPicDetail(View view, String url) {
        ToastHelper.showShort("看图");
    }
}
