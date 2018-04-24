package com.yun.juimodel.ui.home.m;

import android.view.View;
import com.google.gson.annotations.SerializedName;
import com.yun.Constants;
import com.yun.juimodel.ui.home.v.gamedetail.GameDetailAct;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.collections.JObservableList;

/**
 * @another 江祖赟
 * @date 2017/12/25.
 */
public class GameDetail {

    /**
     * info : {"id":"游戏id","video":"视频url","icon":"游戏icon","name":"游戏名称","score":"评分","my_score":"我的评分","fans_num":"关注人数","down_num":"下载次数","share_url":"分享地址","comment_num":"评论数量","is_follow":"是否关注","report_url":"游戏投诉h5地址","tag":[{"id":"标签id","name":"标签名称"}]}
     * detail : {"is_vertical":"是否竖屏","pic":["展示图"],"describe":"游戏简介","developer":"开发商","version":"当前版本","size":"大小","update_time":"更新时间","language":"语言","authority":"权限","update_content":"更新内容","recommend":[{"id":"游戏id","icon":"游戏icon","title":"游戏名称","price":"游戏价格"}]}
     */

    @SerializedName("info") public GameInfo info;
    @SerializedName("detail") public DetailBean detail;

    public static class DetailBean {
        /**
         * is_vertical : 是否竖屏
         * pic : ["展示图"]
         * describe : 游戏简介
         * developer : 开发商
         * version : 当前版本
         * size : 大小
         * update_time : 更新时间
         * language : 语言
         * authority : 权限
         * update_content : 更新内容
         * recommend : [{"id":"游戏id","icon":"游戏icon","title":"游戏名称","price":"游戏价格"}]
         */

        @SerializedName("is_vertical") public String isVertical;
        @SerializedName("describe") public String describe = "游戏简介-lalalla";
        @SerializedName("developer") public String developer = "华为";
        @SerializedName("version") public String version = "1.1.1";
        @SerializedName("size") public String size;
        @SerializedName("update_time") public String updateTime;
        @SerializedName("language") public String language = "中文";
        @SerializedName("authority") public String authority;
        @SerializedName("url") public String downlodurl;
        @SerializedName("update_content") public String updateContent;
        @SerializedName("pic") public List<String> pic;
        @SerializedName("recommend") public List<RecommendBean> recommed;
        public JObservableList<String> gamePics = new JObservableList<>();
        public JObservableList<RecommendBean> recimGames = new JObservableList<>();
    }

    public static class RecommendBean implements ShowDetail {
        /**
         * id : 游戏id
         * icon : 游戏icon
         * title : 游戏名称
         * price : 游戏价格
         */

        @SerializedName("id") public String id;
        @SerializedName("icon") public String icon = Constants.Temp.url1;
        @SerializedName("title") public String title = "游戏名字";
        @SerializedName("price") public String price;


        @Override public void showDetail(View view) {
            GameDetailAct.startAct4View(view,id);
        }
    }
}