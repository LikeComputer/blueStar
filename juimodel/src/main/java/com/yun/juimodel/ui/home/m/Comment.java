package com.yun.juimodel.ui.home.m;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import com.blueprint.LibApp;
import com.blueprint.helper.ToastHelper;
import com.google.gson.annotations.SerializedName;
import com.yun.juimodel.BR;
import com.yun.juimodel.R;
import com.yun.juimodel.data.PageDataWrapper;
import com.yun.juimodel.ui.home.v.gamedetail.CommentDetailAct;
import com.yun.juimodel.ui.home.v.gamedetail.Game2CommentAct;
import java.util.Random;
import me.tatarka.bindingcollectionadapter2.itembindings.ExtrasBindViewModel;

import static com.yun.helper.Data2UIhelper.formartTime;

/**
 * @another 江祖赟
 * @date 2018/1/2.
 */
public class Comment {

    /**
     * score_list : {"score1":"","score2":"","score3":"","score4":"","score5":""}
     * list : [{"id":"评论id","uid":"uid","nickname":"用户昵称","score":"用户对游戏的评分","level":"用户等级","identity":"认证","content":"评论内容","add_time":"评论时间","device":"设备","praise_num":"点赞数","pour_praise_num":"倒赞数","comment_num":"评论数","report_url":"评论投诉h5地址"}]
     */
    @SerializedName("info") public GameInfo info;
    @SerializedName("score_list") public ScoreListBean scoreList;
    @SerializedName("comment") public PageDataWrapper<CommentBean> pageList;

    public static class ScoreListBean extends BaseObservable {
        /**
         * score1 :
         * score2 :
         * score3 :
         * score4 :
         * score5 :
         */
        public String gameId;
        @SerializedName("score1") public String score1;
        @SerializedName("score2") public String score2;
        @SerializedName("score3") public String score3;
        @SerializedName("score4") public String score4;
        @SerializedName("score5") public String score5;
        @SerializedName("comment_num") public String commentNum = "12w";
        @SerializedName("score") public float score = new Random().nextInt(10);
        @SerializedName("my_score") public float myScore;
        @SerializedName("uid") public String uid;
        @SerializedName("content") public String content;


        @Bindable public boolean isScored() {
            return myScore > 0;
        }


        public void setMyScore(float myScore) {
            this.myScore = myScore;
            notifyPropertyChanged(BR.scored);
        }


        public String getCommentNum() {
            return LibApp.findString(R.string.game_detail_comm_scom_num, commentNum);
        }


        public void toEditComment(View view) {
            ToastHelper.showShort("编辑");
        }

        public void toComment(View view) {
            Game2CommentAct.startAct4View(view,content,myScore,gameId);
        }
    }

    public static class CommentBean extends ExtrasBindViewModel implements ShowDetail {
        /**
         * id : 评论id
         * uid : uid
         * nickname : 用户昵称
         * score : 用户对游戏的评分
         * level : 用户等级
         * identity : 认证
         * content : 评论内容
         * add_time : 评论时间
         * device : 设备
         * praise_num : 点赞数
         * pour_praise_num : 倒赞数
         * comment_num : 评论数
         * report_url : 评论投诉h5地址
         * "is_comment_detail":"true"//评论详情处的数据 恒为true
         * "is_praise":"我是否点赞",
         * "is_pour_praise":"我是否倒赞",
         */

        @SerializedName("id") public String id;
        @SerializedName("uid") public String uid;
        @SerializedName("nickname") public String nickname = "迷你先生";
        @SerializedName("score") public float score = new Random().nextInt(10);
        @SerializedName("level") public int level =new Random().nextInt(26);
        @SerializedName("identity") public String identity;
        @SerializedName("content") public String content = "评论内容";
        @SerializedName("add_time") public long addTime;
        @SerializedName("device") public String device = "小米7";
        @SerializedName("praise_num") public String praiseNum = "23";
        @SerializedName("pour_praise_num") public String pourPraiseNum = "24";
        @SerializedName("comment_num") public String commentNum = "33";
        @SerializedName("report_url") public String reportUrl = "投诉";
        @SerializedName("is_comment_detail") public boolean isCommentDetail;
        @Bindable @SerializedName("is_praise") public boolean isPraise = new Random().nextBoolean();
        @Bindable @SerializedName("is_pour_praise") public boolean isPourPraise = new Random().nextBoolean();


        public String getCommentDetailNum() {
            return LibApp.findString(R.string.game_detail_comm_detail_num, commentNum);
        }


        public String getTimeDevice() {
            return LibApp.findString(R.string.common_splice_2param, formartTime(addTime), device);
        }


        public void setPraise(boolean praise) {
            isPraise = praise;
            notifyPropertyChanged(BR.isPraise);
        }


        public void setPourPraise(boolean pourPraise) {
            isPourPraise = pourPraise;
            notifyPropertyChanged(BR.isPourPraise);
        }


        @Override public void showDetail(View view) {
            if (!isCommentDetail) {
                CommentDetailAct.startAct4View(view);
            }
        }


        public CommentBean asDetail() {
            isCommentDetail = true;
            return this;
        }


        public void seeUserDetail(View view) {
            ToastHelper.showShort("查看用户详情");
        }


        public void actionZan(boolean zan) {
            ToastHelper.showShort(String.valueOf(zan));
        }


        public void complaint(View view) {
            ToastHelper.showShort("投诉");
        }
    }
}
