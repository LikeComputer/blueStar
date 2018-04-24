package com.yun.juimodel.ui.home.m;

import android.text.SpannableString;
import android.view.View;
import com.blueprint.LibApp;
import com.blueprint.helper.ToastHelper;
import com.google.gson.annotations.SerializedName;
import com.yun.helper.Data2UIhelper;
import com.yun.juimodel.R;
import com.yun.juimodel.data.commbean.CarouselBean;
import com.yun.juimodel.data.commbean.TagBean;
import com.yun.juimodel.ui.home.v.gamedetail.GameDetailAct;
import com.yun.juimodel.ui.home.v.HomeMoreAct;
import com.yun.juimodel.ui.home.v.More2BookAct;
import java.util.List;
import java.util.Random;
import jzy.easybindpagelist.loadmorehelper.BaseLoadmoreViewModel;

import static com.yun.juimodel.ui.home.v.gamedetail.GameDetailAct.GAMESTATE_NORMAL;
import static com.yun.juimodel.ui.home.v.More2BookAct.HOME_MORE_FINDGAME;
import static me.tatarka.bindingcollectionadapter2.Utils.hightLightStrParser;

/**
 * @another 江祖赟
 * @date 2017/12/21.
 * 首页
 */
public class Home {

    /**
     * carousel : [{"pic":"展示图","url":"内置跳转","tag":"标签，比如：广告"}]
     * resource : [{"id":"游戏id","icon":"游戏icon","name":"游戏名称"}]
     * find_game : {"id":"游戏id","link":"游戏海报或者视频介绍地址","state":"状态：0-正常；1-预约","score":"评分","name":"游戏名称","describe":"游戏简介"}
     * hot_recommend : [{"id":"游戏id","uid":"uid","pic":"游戏展示图","name":"游戏名称","score":"评分","nickname":"用户昵称","content":"评论内容"}]
     * advertisement : {"id":"广告id","link":"游戏海报或者视频介绍地址","name":"游戏名称","describe":"游戏简介"}
     * user_recommend : [{"id":"游戏id","uid":"uid","pic":"游戏展示图","name":"游戏名称","nickname":"用户昵称","content":"评论内容","comment_num":"回复数量","add_time":"评论时间"}]
     * game_book : [{"id":"游戏id","pic":"游戏展示图","is_book":"是否预约中","score":"评分","name":"游戏名称"}]
     * welfare : [{"id":"礼包id","icon":"游戏icon","name":"游戏名称","title":"礼包名称","surplus":"剩余数量","has_brought":"已领取数量"}]
     * game_recommend : [{"id":"游戏id","pic":"游戏展示图","score":"评分","name":"游戏名称","describe":"游戏简介"}]
     */

    @SerializedName("find_game") public FindGameBean findGame;
    @SerializedName("advertisement") public AdvertisementBean advertisement;
    @SerializedName("carousel") public List<CarouselBean> carousel;
    @SerializedName("resource") public List<ResourceBean> resource;
    @SerializedName("hot_recommend") public List<HotRecommendBean> hotRecommend;
    @SerializedName("user_recommend") public List<UserRecommendBean> userRecommend;
    @SerializedName("game_book") public List<GameBookBean> gameBook;
    @SerializedName("welfare") public List<WelfareBean> welfare;
    @SerializedName("game_recommend") public List<GameRecommendBean> gameRecommend;

    public static class FindGameBean extends TagBean.BaseTagBean {

        /**
         * id : 游戏id
         * link : 游戏海报或者视频介绍地址
         * state : 状态：1-正常；0-预约
         * score : 评分
         * name : 游戏名称
         * describe : 游戏简介
         * "url":"游戏资源h5详情",
         */

        @SerializedName("id") public String id = "0";
        //首页发现游戏  更多 游戏预约，发现游戏
        @SerializedName("link") public String link = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
        //首页游戏预约图片
        @SerializedName("pic") public String pic = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
        @SerializedName("state") public int state = new Random().nextInt(2);
        @SerializedName("is_book") public boolean isBook = new Random().nextBoolean();
        @SerializedName("score") public float score = 9.5f;
        @SerializedName("name") public String name = "name";
        @SerializedName("comment_num") public String commentNum = "112";
        @SerializedName("url") public String url = "descibe";
        @SerializedName("describe") public String describe = getBookState();

        public void showDetail(View view) {
            GameDetailAct.startAct4View(view, id);
        }


        //游戏的预约状态
        public String getBookState() {
            if (state == GAMESTATE_NORMAL) {
                return LibApp.findString(R.string.home_item_gamebook_play);
            }
            else if (isBook) {
                //已预约
                return LibApp.findString(R.string.home_item_gamebook_has_booked);
            }
            else {
                return LibApp.findString(R.string.home_item_gamebook_booked);
            }
        }


        public void toBookPlay(View view) {
            if (state == GAMESTATE_NORMAL) {
                //游戏可玩
                GameDetailAct.startAct4View(view, id);
            }
            else {
                //发起预约请求
                ToastHelper.showShort("发起预约请求");
            }
        }


        //发现游戏查看更多
        public void showMore(View view) {
            More2BookAct.startAct4View(view, HOME_MORE_FINDGAME);
        }


        public CharSequence getZScore() {
            return Data2UIhelper.getZScore(score);
        }



    }

    /**
     * 新游预约
     */
    public static class GameBookBean extends FindGameBean {
        //和 发现游戏有细微差别 更多和首页也有区别


        /**
         * 在{@link ItemRecvViewModel}中处理
         */
        public void showMore(View view) {
        }
    }

    public static class AdvertisementBean {
        /**
         * id : 广告id
         * link : 游戏海报或者视频介绍地址
         * name : 游戏名称
         * describe : 游戏简介
         */

        @SerializedName("id") public String id = "0";
        @SerializedName("link") public String link = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
        @SerializedName("name") public String name = "广告";
        @SerializedName("describe") public String describe = "广告描述";


        public void showDetail(View view) {
            GameDetailAct.startAct4View(view, id);
        }


        public void close(View view) {
            ToastHelper.showShort("推荐有戏详情");
        }
    }

    public static class ResourceBean implements ShowDetail {
        /**
         * id : 游戏id
         * icon : 游戏icon
         * name : 游戏名称
         */
        public static boolean styleLone = true;
        @SerializedName("id") public String id = "0";
        @SerializedName("icon") public String icon = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
        @SerializedName("name") public String name = "名字";
        @SerializedName("url") public String url = "名字";


        public void showDetail(View view) {
            ToastHelper.showShort("游戏资源 " + url);
        }


        public void showMore(View view) {
            ToastHelper.showShort("详情：" + id);
        }
    }

    public static class HotRecommendBean {
        /**
         * id : 游戏id
         * uid : uid
         * pic : 游戏展示图
         * name : 游戏名称
         * score : 评分
         * nickname : 用户昵称
         * content : 评论内容
         */

        @SerializedName("id") public String id = "0";
        @SerializedName("uid") public String uid = "0000";
        @SerializedName("pic") public String pic = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
        @SerializedName("name") public String name = "name";
        @SerializedName("score") public float score = 9;
        @SerializedName("nickname") public String nickname = "nickname";
        @SerializedName("content") public String content = "content";


        public void showDetail(View view) {
            GameDetailAct.startAct4View(view, id);
        }


        public String getAvator() {
            return Data2UIhelper.getMiddlePortrait(uid);
        }


        public CharSequence getZScore() {
            return Data2UIhelper.getZScore(score);
        }
    }

    public static class UserRecommendBean {
        /**
         * id : 游戏id
         * uid : uid
         * pic : 游戏展示图
         * name : 游戏名称
         * nickname : 用户昵称
         * content : 评论内容
         * comment_num : 回复数量
         * add_time : 评论时间
         */

        public boolean isFirst;
        public boolean isLast;

        @SerializedName("id") public String id = "0";
        @SerializedName("uid") public String uid;
        @SerializedName("pic") public String pic = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
        @SerializedName("name") public String name = "name33";
        @SerializedName("nickname") public String nickname = "nickname";
        @SerializedName("content") public String content = "描述描述描述描述描述描述描述描述描述描述描述描述描述描述";
        @SerializedName("comment_num") public String commentNum = "10";
        @SerializedName("add_time") public long addTime = 111;


        public String getPublishTime() {
            return Data2UIhelper.formartTime(addTime);
        }


        public String getGameName() {
            return "<<" + name + ">>";
        }


        public UserRecommendBean castFirst() {
            isFirst = true;
            return this;
        }


        public UserRecommendBean castLast() {
            isLast = true;
            return this;
        }


        public void showDetail(View view) {
            GameDetailAct.startAct4View(view, true, id);
        }


        public void showMore(View view) {
            HomeMoreAct.startAct4View(view, HomeMoreAct.HOME_MORE_USERRECOM);
        }


        public String getAvator() {
            return Data2UIhelper.getMiddlePortrait(uid);
        }
    }

    public static class WelfareBean {
        /**
         * id : 礼包id
         * icon : 游戏icon
         * name : 游戏名称
         * title : 礼包名称
         * surplus : 剩余数量
         * has_brought : 已领取数量
         * "url":"礼包详情h5页面"
         * "is_obtain":"是否已领取"
         */

        @SerializedName("id") public String id = "0";
        @SerializedName("icon") public String icon = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
        @SerializedName("name") public String name = "名字";
        @SerializedName("title") public String title = "标题";
        @SerializedName("surplus") public String surplus = "10";
        @SerializedName("has_brought") public String hasBrought = "1";
        @SerializedName("url") public String url = "1";
        @SerializedName("is_obtain") public boolean isObtain = new Random().nextBoolean();


        public void showDetail(View view) {
            ToastHelper.showShort("福利中心详情");
        }


        //搜的游戏名字 高亮key
        public CharSequence getGameName() {
            return hightLightStrParser(new SpannableString(name), "[" + BaseLoadmoreViewModel.CURRENT_SEARCH_KEY + "]", LibApp.findColor(R.color.tv_orign_ff88));
        }


        //首页显示的福利信息
        public CharSequence getDetail() {
            int hightColor = LibApp.findColor(R.color.tv_orign_ff88);
            return hightLightStrParser(hightLightStrParser(new SpannableString(LibApp.findString(R.string.home_item_welfare_detail, hasBrought, surplus)), hasBrought, hightColor),
                    surplus, hightColor);
        }


        /**
         * 更多列表 显示的福利信息
         */
        public CharSequence getDetail2More() {
            return LibApp.findString(R.string.more_wealfare_detail, hasBrought, surplus);
        }
    }

    public static class GameRecommendBean {
        /**
         * id : 游戏id
         * pic : 游戏展示图
         * score : 评分
         * name : 游戏名称
         * describe : 游戏简介
         */

        @SerializedName("id") public String id = "0";
        @SerializedName("pic") public String pic = "http://img.hb.aicdn.com/fb61026e745e7d6c4c36c64356f924caf1fc78e15d1d0-ciHN1i_fw658";
        @SerializedName("score") public float score;
        @SerializedName("name") public String name = "游戏推荐";
        @SerializedName("describe") public String describe;


        public CharSequence getZScore() {
            return Data2UIhelper.getZScore(score);
        }


        public void showDetail(View view) {
            GameDetailAct.startAct4View(view, id);
        }
    }
}
