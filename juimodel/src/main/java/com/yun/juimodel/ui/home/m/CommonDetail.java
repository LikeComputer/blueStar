package com.yun.juimodel.ui.home.m;

import android.app.Activity;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import com.yun.juimodel.R;
import com.yun.juimodel.data.PageDataWrapper;
import com.yun.juimodel.ui.home.v.gamedetail.CommentDetailAct;
import com.blueprint.LibApp;
import com.blueprint.helper.ToastHelper;
import com.google.gson.annotations.SerializedName;
import me.tatarka.bindingcollectionadapter2.collections.IRecvDataDiff;
import me.tatarka.bindingcollectionadapter2.itembindings.ExtrasBindViewModel;

import static com.yun.helper.Data2UIhelper.dateDistance2;
import static me.tatarka.bindingcollectionadapter2.Utils.hightLightStrParser;

/**
 * @another 江祖赟
 * @date 2018/1/4.
 */
public class CommonDetail {

    /**
     * game : {"id":"游戏id","icon":"游戏icon","name":"游戏名称","score":"评分"}
     * comment : {"id":"评论id","uid":"uid","nickname":"用户昵称","score":"用户对游戏的评分","level":"用户等级","identity":"认证","content":"评论内容","add_time":"评论时间","device":"设备","praise_num":"点赞数","pour_praise_num":"倒赞数","comment_num":"评论数","report_url":"评论投诉h5地址"}
     * reply : {"page":"页码int","hasNext":"是否有下一页(true或者false)","list":[{"id":"回复id","uid":"uid","nickname":"用户昵称","uid_to":"uid","nickname_to":"用户昵称","level":"用户等级","identity":"认证","content":"评论内容","add_time":"评论时间"}]}
     */

    @SerializedName("game") public GameInfo game;
    @SerializedName("comment") public Comment.CommentBean comment;
    @SerializedName("reply") public PageDataWrapper<ReplyDetail> reply;

    /**
     * list : [{"id":"回复id","uid":"uid","nickname":"用户昵称","uid_to":"uid","nickname_to":"用户昵称","level":"用户等级","identity":"认证","content":"评论内容","add_time":"评论时间"}]
     */
    public static class ReplyDetail extends ExtrasBindViewModel {
        /**
         * id : 回复id
         * uid : uid
         * nickname : 用户昵称
         * uid_to : uid
         * nickname_to : 用户昵称
         * level : 用户等级
         * identity : 认证
         * content : 评论内容
         * add_time : 评论时间
         */

        @SerializedName("id") public String id;
        @SerializedName("uid") public String uid;
        @SerializedName("nickname") public String nickname = "渣渣黄";
        @SerializedName("uid_to") public String uidTo = "uid2";
        @SerializedName("nickname_to") public String nicknameTo = "呵呵呵";
        @SerializedName("level") public String level = "等级";
        @SerializedName("identity") public String identity;
        @SerializedName("content") public String content = "连昌宫中满宫竹，岁久无人森似束。\n" + "又有墙头千叶桃，风动落花红蔌蔌。\n" + "宫边老翁为余泣，小年进食曾因入。\n" + "上皇正在望仙楼，太真同凭阑干立。";
        @SerializedName("add_time") public String addTime = "0";


        public String getPublishTime() {
            return dateDistance2(addTime);
        }


        public CharSequence getCommentContent() {
            if (TextUtils.isEmpty(nicknameTo)) {
                return content;
            }
            else {
                SpannableStringBuilder builder = new SpannableStringBuilder();
                builder.append("回复 ").append(nicknameTo).append(" ：").append(content);
                SpannableString spannableString = new SpannableString(builder);
                hightLightStrParser(spannableString, nicknameTo, Color.TRANSPARENT);
                return spannableString;
            }
        }


        //是否被回复过
        public boolean isReplyed() {
            return !TextUtils.isEmpty(nicknameTo);
        }


        public CharSequence getReplyRelation() {
            if (TextUtils.isEmpty(nicknameTo)) {
                return "";
            }
            else {
                SpannableStringBuilder builder = new SpannableStringBuilder();
                builder.append("回复 ").append(nicknameTo).append(" ：");
                SpannableString spannableString = new SpannableString(builder);
                hightLightStrParser(hightLightStrParser(spannableString, nicknameTo, LibApp.findColor(R.color.tv_orign_ff88)),
                        "回复", Color.TRANSPARENT);
                return spannableString;
            }
        }


        //点击头像看详情
        public void seeUserDetail(View view) {
            ToastHelper.showShort("查看用户详情");
        }


        //查看被回复的用户
        public void seeReplyDetail(View view) {
            ToastHelper.showShort("查看用户详情");
        }


        public void replyMe(View view) {
            Activity act4View = LibApp.getAct4View(view);
            if (act4View != null && act4View instanceof CommentDetailAct) {
                //((CommentDetailAct) act4View).showReplyDialog(nickname,uid);
                ToastHelper.showShort("回复异常");
            }
            else {
                ToastHelper.showShort("回复异常");
            }
        }


        @Override public boolean areItemsTheSame(IRecvDataDiff oldData, IRecvDataDiff newData) {
            return super.areItemsTheSame(oldData, newData);
        }
    }
}
