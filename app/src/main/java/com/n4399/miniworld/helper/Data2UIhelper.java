package com.n4399.miniworld.helper;

import android.graphics.Color;
import android.widget.TextView;

import com.blueprint.helper.UIhelper;
import com.blueprint.widget.JDownloadProgView;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.MsgCardBean;

import static com.blueprint.LibApp.findColor;

/**
 * @another 江祖赟
 * @date 2017/7/4.
 */
public class Data2UIhelper {

    public static String downloadMsg(int downs, float size){
        return downs+"---"+size;
    }

    public static void downloadProbutton(JDownloadProgView jProgView, String s, String e){
        jProgView.setProgColors(findColor(R.color.item_recom_progress), findColor(R.color.colorAccent));
        jProgView.setTextColors(findColor(R.color.colorAccent), findColor(android.R.color.white));
//        jProgView.setTextColors(findColor(android.R.color.white), findColor(android.R.color.white));
        jProgView.setOutLineColor(Color.TRANSPARENT);
        jProgView.setRadio(Integer.MAX_VALUE);
        jProgView.setTextSD(s, e);
    }

    public static void configMapLabelStyle(TextView labelView, String label){
        int color = Color.RED;
        if(MsgCardBean.GAME.equals(label)) {
            color = Color.BLACK;
            label = "游戏";
        }else if(MsgCardBean.NEWS_TEXT.equals(label)) {
            color = Color.GREEN;
            label = "新闻";
        }else if(MsgCardBean.RAIDERS_VIDEO.equals(label)) {
            color = Color.YELLOW;
            label = "攻略";
        }
        UIhelper.RoundBgText(labelView, color);
        labelView.setText(label);
    }

}
