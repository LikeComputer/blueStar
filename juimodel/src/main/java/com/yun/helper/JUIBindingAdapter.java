package com.yun.helper;

import android.databinding.BindingAdapter;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.blueprint.LibApp;
import com.blueprint.helper.CheckHelper;
import com.blueprint.widget.JFlowLayout;
import com.blueprint.widget.JRatingBar;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yun.juimodel.R;
import com.yun.juimodel.ui.home.m.GameInfo;
import com.yun.juimodel.ui.home.m.Home;
import com.yun.widget.AvatorImageView;
import com.yun.widget.LoopMZViewPager;
import java.util.List;

import static com.blueprint.widget.JToolbar.dp2px;
import static com.yun.juimodel.ui.home.v.gamedetail.GameDetailAct.GAMESTATE_NORMAL;

/**
 * @another 江祖赟
 * @date 2017/12/25.
 */
public class JUIBindingAdapter {

    @BindingAdapter(value = { "loopItems" }, requireAll = false)
    public static void configJViewpager(LoopMZViewPager viewPager, List<? extends BindingReusePagerAdapter.JVpItem> items) {
        if(items != null && items.size() > 0) {
            viewPager.setItems(items);
        }
    }


    @BindingAdapter("select") public static void setSelect(View view, boolean isSelected) {
        view.setSelected(isSelected);
    }


    @BindingAdapter("levelbg") public static void setLevelBg(TextView view, int level) {
        if(level > 20) {
            view.setBackgroundResource(R.drawable.bg_red_round_2);
            view.setText(LibApp.findString(R.string.common_user_level, level));
        } else if(level > 10) {
            view.setBackgroundResource(R.drawable.bg_orign_round_2);
            view.setText(LibApp.findString(R.string.common_user_level, level));
        } else {
            view.setBackgroundResource(R.drawable.bg_blue_round_2);
            view.setText(LibApp.findString(R.string.common_user_level_, level));
        }
    }


    //更多的发现游戏中 按钮的状态 我要玩/去预约/已预约
    @BindingAdapter("gameBookstate") public static void configGameBookState(TextView tv, Home.FindGameBean findGameBean) {
        if(findGameBean.state == GAMESTATE_NORMAL) {
            //游戏可玩状态
            tv.setSelected(true);
            tv.setEnabled(true);
        } else if(findGameBean.isBook) {
            tv.setEnabled(false);
            tv.setSelected(false);
        }
        tv.setText(findGameBean.getBookState());
    }


    //游戏详情中 底部按钮状态 下载 预约中/已预约
    @BindingAdapter("gameInfostate") public static void configGameInfoState(TextView tv, GameInfo gameInfo) {
        if(gameInfo != null) {
            if(gameInfo.state == GAMESTATE_NORMAL) {
                //下载游戏
                tv.setSelected(true);
                tv.setEnabled(true);
            } else if(gameInfo.isBook) {
                tv.setSelected(false);
                //已预约
                tv.setEnabled(false);
            }
            tv.setText(gameInfo.getGameState());
        }
    }


    //福利中心 福利状态，废除 【需求一直显示去领取】
    @BindingAdapter("wealfareState") public static void configWealfareState(TextView tv, boolean isObtain) {
        if(isObtain) {
            tv.setText("已领取");
            tv.setSelected(true);
        } else {
            tv.setText("去领取");
            tv.setSelected(false);
        }
    }


    //游戏详情中 游戏的关注状态
    @BindingAdapter("gameAttention") public static void setGameAttention(TextView view, boolean isFollow) {
        if(isFollow) {
            //已关注
            view.setText(LibApp.findString(R.string.game_detail_attention_ed));
        } else {
            //没关注
            view.setText(LibApp.findString(R.string.game_detail_attention));
        }
        view.setSelected(isFollow);
    }


    //标签
    @BindingAdapter(value = { "keyList", "selectListener" }, requireAll = false)
    public static void setKeylist(JFlowLayout flowLayout, List<String> keys, JFlowLayout.OnItemSelectedListener selectedListener) {
        if(CheckHelper.safeLists(keys)) {
            flowLayout.removeAllViews();
            flowLayout.setVisibility(View.VISIBLE);
            flowLayout.setHorizontalSpacing(dp2px(9))
                      .setVerticalSpacing(dp2px(9))
                      .setTextSize(13)
                      .setItemTvColor(LibApp.findColor(R.color.gray_8b8b))
                      .setItemBackgroundResource(R.drawable.common_bg_flowitem);
            flowLayout.setOnItemSelectedListener(selectedListener);
            flowLayout.addContents(keys);
        } else {
            flowLayout.setVisibility(View.GONE);
        }
    }


    //标签
    @BindingAdapter(value = { "rankkeyList", "rankSelectListener" }, requireAll = false)
    public static void setRankKeylist(JFlowLayout flowLayout, List<String> keys, JFlowLayout.OnItemSelectedListener selectedListener) {
        if(CheckHelper.safeLists(keys)) {
            flowLayout.removeAllViews();
            flowLayout.setVisibility(View.VISIBLE);
            flowLayout.setHorizontalSpacing(dp2px(9))
                      .setVerticalSpacing(dp2px(9))
                      .setTextSize(11)
                      .setItemTvColor(LibApp.findColor(R.color.gray_8b8b))
                      .setItemBackgroundResource(R.drawable.rank_bg_flowitem);
            flowLayout.setOnItemSelectedListener(selectedListener);
            flowLayout.addContents(keys);
        } else {
            flowLayout.setVisibility(View.GONE);
        }
    }


    //标签
    @BindingAdapter("rbProgress") public static void setRatingbarProgress(JRatingBar jRatingBar, float rating) {
        jRatingBar.setMark(rating);
    }


    /**
     * 加载图片
     */
    @BindingAdapter(value = { "loadImageUrl", "defaultImage" }, requireAll = false)
    public static void loadImage(SimpleDraweeView imageView, String imageUrl, Drawable defaultImage) {
        if(imageView.getVisibility() == View.VISIBLE) {
            if(!TextUtils.isEmpty(imageUrl)) {
                imageView.setImageURI(Uri.parse(imageUrl));
                return;
            }
            if(null == defaultImage) {
                defaultImage = LibApp.findDrawable(R.drawable.icon_btn_back);
            }
            imageView.setImageDrawable(defaultImage);
        }
        // imageView.setController( defaultImage);
    }


    /**
     * 加载网络图片圆角
     */
    @BindingAdapter(value = { "loadRoundImageUrl", "round", "defaultImage" }, requireAll = false)
    public static void loadRoundImage(SimpleDraweeView imageView, String imageUrl, float pix, Drawable defaultImage) {
        if(imageView.getVisibility() == View.VISIBLE) {
            if(!TextUtils.isEmpty(imageUrl)) {
                RoundingParams roundingParams = new RoundingParams();
                roundingParams.setCornersRadius(pix);
                imageView.getHierarchy().setRoundingParams(roundingParams);
                imageView.setImageURI(Uri.parse(imageUrl));
                return;
            }
            if(null == defaultImage) {
                defaultImage = LibApp.findDrawable(R.drawable.btn_back_38_darkgray);
            }
            imageView.setImageDrawable(defaultImage);
        }
    }


    /**
     * 加载圆形图片(带有默认颜色)
     */
    @BindingAdapter(value = { "loadAvator" }, requireAll = false)
    public static void loadCircleImage(AvatorImageView imageView, String imageUrl) {
        if(imageView.getVisibility() == View.VISIBLE) {
            if(!TextUtils.isEmpty(imageUrl)) {
                RoundingParams roundingParams = new RoundingParams();
                roundingParams.setRoundAsCircle(true);
                imageView.getHierarchy().setRoundingParams(roundingParams);
                imageView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP);
                PointF point = new PointF();
                point.set(0.5f, 0.0f);
                imageView.getHierarchy().setActualImageFocusPoint(point);
                imageView.setImageURI(Uri.parse(imageUrl));
                return;
            }
            imageView.setImageResource(R.drawable.defalut_holder_round_8);
        }
    }
}
