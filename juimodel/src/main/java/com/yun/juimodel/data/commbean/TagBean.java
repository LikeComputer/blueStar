package com.yun.juimodel.data.commbean;

import android.view.View;
import com.blueprint.helper.ToastHelper;
import com.blueprint.widget.JFlowLayout;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @another 江祖赟
 * @date 2018/1/9.
 */
public class TagBean {
    /**
     * id : id
     * title : 名字
     */

    @SerializedName("id") public String id;
    @SerializedName("name") public String name;

    public static class BaseTagBean implements JFlowLayout.OnItemSelectedListener{
        public List<String> tagstrs = new ArrayList<>();
        @SerializedName("tag") public List<TagBean> tag = new ArrayList<>();


        public List<String> getFlowTags() {
            if (tagstrs.isEmpty() && tag != null) {
                //模拟数据
                for (int i = 0; i < new Random().nextInt(4); i++) {
                    TagBean tagBean = new TagBean();
                    tagBean.id = String.valueOf(new Random().nextInt(16));
                    tagBean.name = "随便tag";
                    tag.add(tagBean);
                }
                for (TagBean tagBean : tag) {
                    tagstrs.add(tagBean.name);
                }
            }
            return tagstrs;
        }


        @Override public void onItemSelected(View v, int position) {
            ToastHelper.showShort(tag.get(position).name+"==="+tag.get(position).id);
        }
    }
}
