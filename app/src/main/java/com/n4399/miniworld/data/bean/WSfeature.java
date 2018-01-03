package com.n4399.miniworld.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.blueprint.Consistent;
import com.blueprint.adapter.IRecvData;
import com.blueprint.adapter.RecyclerHolder;
import com.n4399.miniworld.R;

import java.util.List;
import java.util.Random;

/**
 * @another 江祖赟
 * @date 2017/7/5.
 * 工坊精选
 */
public class WSfeature {

    /**
     * page : 页码int
     * hasNext : 是否有下一页(true或者false)
     * typelist : [{"id":"分类id","icon":"分类icon","title":"分类名称"}]
     * list : [{"id":"id","title":"标题","pic":"图片地址","url":"链接地址","typeName":"分类名称","author":"作者","down":"下载量","size":"大小"}]
     */

    private String page;
    private boolean hasNext;
    private List<TypelistBean> typelist;
    private List<HotMapBean> list;

    public String getPage(){
        return page;
    }

    public void setPage(String page){
        this.page = page;
    }

    public boolean getHasNext(){
        return hasNext;
    }

    public void setHasNext(boolean hasNext){
        this.hasNext = hasNext;
    }

    public List<TypelistBean> getTypelist(){
        return typelist;
    }

    public void setTypelist(List<TypelistBean> typelist){
        this.typelist = typelist;
    }

    public List<HotMapBean> getList(){
        return list;
    }

    public void setList(List<HotMapBean> list){
        this.list = list;
    }

    public static class TypelistBean implements IRecvData, Parcelable {
        /**
         * id : 分类id
         * icon : 分类icon
         * title : 分类名称
         */

        private int id;
        private String icon = Consistent.TEMP.ICON;
        private String title = "类"+new Random().nextInt(10);

        public int getId(){
            return id;
        }

        public void setId(int id){
            this.id = id;
        }

        public String getIcon(){
            return icon;
        }

        public void setIcon(String icon){
            this.icon = icon;
        }

        public String getTitle(){
            return title;
        }

        public void setTitle(String title){
            this.title = title;
        }

        public void bindHolder(RecyclerHolder holder){
            holder.setImageUrl(R.id.item_wshop_feature_sort_icon, icon)
                    .setText(R.id.item_wshop_feature_sort_title, title);
        }

        @Override
        public int describeContents(){
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags){
            dest.writeInt(this.id);
            dest.writeString(this.icon);
            dest.writeString(this.title);
        }

        public TypelistBean(){
        }

        protected TypelistBean(Parcel in){
            this.id = in.readInt();
            this.icon = in.readString();
            this.title = in.readString();
        }

        public static final Parcelable.Creator<TypelistBean> CREATOR = new Parcelable.Creator<TypelistBean>() {
            @Override
            public TypelistBean createFromParcel(Parcel source){
                return new TypelistBean(source);
            }

            @Override
            public TypelistBean[] newArray(int size){
                return new TypelistBean[size];
            }
        };
    }

}
