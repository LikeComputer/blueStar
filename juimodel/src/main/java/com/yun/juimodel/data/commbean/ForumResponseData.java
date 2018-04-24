package com.yun.juimodel.data.commbean;

import com.google.gson.annotations.SerializedName;

/**
 * 论坛接口相关
 *
 * @author 徐智伟
 * @create 2017/5/4
 */

public class ForumResponseData<T> {

  public static final int CODE_RESPONSE_SUCCESS = 100;

  @SerializedName("code")
  public int code;
  @SerializedName("msg")
  public String msg;
  @SerializedName("result")
  public T result;

  public boolean isResponseSuccess(){
    return code == CODE_RESPONSE_SUCCESS;
  }

  @Override
  public String toString() {
    return "ForumResponseData{" +
        "code=" + code +
        ", msg='" + msg + '\'' +
        ", result=" + result +
        '}';
  }
}
