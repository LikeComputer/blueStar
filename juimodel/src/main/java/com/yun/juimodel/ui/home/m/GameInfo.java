package com.yun.juimodel.ui.home.m;

import android.view.View;
import com.blueprint.LibApp;
import com.blueprint.helper.ToastHelper;
import com.google.gson.annotations.SerializedName;
import com.yun.juimodel.R;
import com.yun.juimodel.data.commbean.TagBean;
import com.yun.juimodel.ui.home.v.gamedetail.GameDetailAct;
import java.util.List;
import java.util.Random;

import static com.yun.juimodel.ui.home.v.gamedetail.GameDetailAct.GAMESTATE_NORMAL;

/**
 * @another 江祖赟
 * @date 2018/1/3.
 * 有系详情头部
 */
public class GameInfo extends TagBean.BaseTagBean implements ShowDetail{
    /**
     * id : 游戏id
     * video : 视频url
     * icon : 游戏icon
     * name : 游戏名称
     * score : 评分
     * my_score : 我的评分
     * fans_num : 关注人数
     * down_num : 下载次数
     * share_url : 分享地址
     * comment_num : 评论数量
     * is_follow : 是否关注
     * report_url : 游戏投诉h5地址
     * state : 状态：0-正常；1-预约
     * tag : [{"id":"标签id","name":"标签名称"}]
     */

    @SerializedName("id") public String id;
    @SerializedName("video") public String video;
    @SerializedName("icon") public String icon = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
    @SerializedName("link") public String link = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
    @SerializedName("url") public String url = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
    @SerializedName("name") public String name = "迷你世界1";
    @SerializedName("score") public float score = new Random().nextInt(10);
    @SerializedName("my_score") public String myScore;
    @SerializedName("fans_num") public String fansNum = "12w";
    @SerializedName("down_num") public String downNum = "3w";
    @SerializedName("share_url") public String shareUrl;
    @SerializedName("comment_num") public String commentNum = "12w";
    @SerializedName("is_follow") public boolean isFollow = new Random().nextBoolean();
    @SerializedName("report_url") public String reportUrl;
    @SerializedName("state") public int state = new Random().nextInt(2);
    @SerializedName("is_book") public boolean isBook = new Random().nextBoolean();

    public String getAttenDownMsg(){
        return LibApp.findString(R.string.game_detail_atten_down_msg, fansNum, downNum);
    }


    public void complaint(View view) {
        ToastHelper.showShort("投诉");
    }


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameInfo gameInfo = (GameInfo) o;

        if (Float.compare(gameInfo.score, score) != 0) return false;
        if (id != null ? !id.equals(gameInfo.id) : gameInfo.id != null) return false;
        if (name != null ? !name.equals(gameInfo.name) : gameInfo.name != null) return false;
        return myScore != null ? myScore.equals(gameInfo.myScore) : gameInfo.myScore == null;
    }


    @Override public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (score != +0.0f ? Float.floatToIntBits(score) : 0);
        result = 31 * result + (myScore != null ? myScore.hashCode() : 0);
        return result;
    }


    public String getGameState() {
        if (state == GAMESTATE_NORMAL) {
            //下载/开始
            return LibApp.findString(R.string.home_item_gamebook_play_download);
        }
        else if (isBook) {
            //已预约
            return LibApp.findString(R.string.home_item_gamebook_has_booked);
        }
        else {
            //预约
            return LibApp.findString(R.string.home_item_gamebook_booked);
        }
    }


    @Override public List<String> getFlowTags() {
        return super.getFlowTags();
    }


    @Override public void showDetail(View view) {
        GameDetailAct.startAct4View(view,id);
    }
}
