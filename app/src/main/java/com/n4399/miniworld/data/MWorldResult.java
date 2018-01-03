package com.n4399.miniworld.data;

/**
 * @another 江祖赟
 * @date 2017/7/5.
 */
public class MWorldResult<T> {

    /**
     * code : 10000
     * message : success
     * data : {}
     */

    private int code;
    private String message;
    private T data;

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

}
