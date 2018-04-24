package com.blueprint.helper;

import io.reactivex.functions.Consumer;

/**
 * @another 江祖赟
 * @date 2018/3/27.
 */
public class SimpleErrorConsumer implements Consumer<Throwable> {
    @Override public void accept(Throwable throwable) throws Exception {
        ToastHelper.toastErrorMsg(throwable);
    }
}
