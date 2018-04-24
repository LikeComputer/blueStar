package com.yun.juimodel.data.commbean;

import android.view.View;
import com.google.gson.annotations.SerializedName;

import static com.yun.Constants.Temp.url3;

/**
 * @another 江祖赟
 * @date 2017/12/21.
 */
public class MsgCardBean implements ShowDetail{
    /**
     * id : id
     * type : 类型(1-资讯，2-视频)
     * title : 标题
     * content : 内容
     * pic : 图片
     * date : 发布时间（时间戳）
     * views : 访问数
     * url :
     */

    @SerializedName("id") public String id;
    @SerializedName("type") public int type;
    @SerializedName("title") public String title = "我怎么听都像放屁";
    @SerializedName("content") public String content = "我是内容，艳福是渣黄";
    @SerializedName("pic") public String pic = url3;
    @SerializedName("date") public String date;
    @SerializedName("views") public String views = "123w";
    @SerializedName("url") public String url;

    public boolean isVideoType() {
        //类型(1-资讯，2-视频)
        return type == 2;
    }


    @Override public void showDetail(View view) {
        //HeadLineGameAct.startAct4View(view,title,"");
    }
}
