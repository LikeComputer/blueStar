package com.n4399.miniworld.helper;

import com.blueprint.widget.JDownloadProgView;

import java.lang.ref.WeakReference;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @author yun.
 * @date 2017/7/9
 * @des [一句话描述]
 * @since [https://github.com/mychoices]
 * <p><a href="https://github.com/mychoices">github</a>
 */
public class JProgressUpdateConsumer implements Consumer {

    private WeakReference<JDownloadProgView> mJProgViewWeakReference;

    public JProgressUpdateConsumer(JDownloadProgView jProgView){
        mJProgViewWeakReference = new WeakReference<JDownloadProgView>(jProgView);
    }

    @Override
    public void accept(@NonNull Object o) throws Exception{
        if(mJProgViewWeakReference.get() != null) {
            mJProgViewWeakReference.get().setProgress(mJProgViewWeakReference.get().getProgress()+0.01f);
        }
    }
}
