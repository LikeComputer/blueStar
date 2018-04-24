package com.blueprint.rx.event;

/**
 * @another 江祖赟
 * @date 2018/4/18.
 * 关闭 activity的事件
 */
public class FinishActEvent<T> {

    public T mExtraData;

    public FinishActEvent() {
    }

    public FinishActEvent(T extraData) {
        this.mExtraData = extraData;
    }
}
