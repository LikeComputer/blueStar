package com.n4399.miniworld.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import static com.blueprint.Consistent.TEMP.PIC1;
import static com.blueprint.helper.CheckHelper.checkObjects;

/**
 * @another 江祖赟
 * @date 2017/6/30.
 */
public class AccountBean implements Parcelable {

    /**
     * uid : 1
     * avatar : 头像地址
     * nickname : 昵称
     * miniid : 迷你号
     * follow : 关注数量
     * fans : 粉丝数量
     */

    protected int uid = 1001;
    protected String avatar = PIC1;
    protected String nickname = "怕卡球";
    protected String miniid = "43992864";
    protected int follow = 4567;
    protected int fans = 12345;

    public static final Creator<AccountBean> CREATOR = new Creator<AccountBean>() {
        @Override
        public AccountBean createFromParcel(Parcel in){
            return new AccountBean(in);
        }

        @Override
        public AccountBean[] newArray(int size){
            return new AccountBean[size];
        }
    };

    public int getUid(){
        return uid;
    }

    public void setUid(int uid){
        this.uid = uid;
    }

    public String getAvatar(){
        return avatar;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getMiniid(){
        return miniid;
    }

    public void setMiniid(String miniid){
        this.miniid = miniid;
    }

    public int getFollow(){
        return follow;
    }

    public void setFollow(int follow){
        this.follow = follow;
    }

    public int getFans(){
        return fans;
    }

    public void setFans(int fans){
        this.fans = fans;
    }

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public boolean equals(Object obj){
        return uid == ( ( (AccountBean)obj ).getUid() ) ? true : checkObjects(miniid) ? miniid
                .equals(( (GuysBean)obj ).getMiniid()) : false;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.uid);
        dest.writeString(this.avatar);
        dest.writeString(this.nickname);
        dest.writeString(this.miniid);
        dest.writeInt(this.follow);
        dest.writeInt(this.fans);
    }

    public AccountBean(){
    }

    protected AccountBean(Parcel in){
        this.uid = in.readInt();
        this.avatar = in.readString();
        this.nickname = in.readString();
        this.miniid = in.readString();
        this.follow = in.readInt();
        this.fans = in.readInt();
    }

    @Override
    public String toString(){
        return "AccountBean{"+"uid="+uid+", nickname='"+nickname+'\''+", miniid='"+miniid+'\''+'}';
    }
}
