package com.n4399.miniworld.vp.webactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;

import com.blueprint.widget.JTitleBar;
import com.n4399.miniworld.vp.basic.JBasicWebViewAt;

import static com.blueprint.Consistent.Common.INTENT_URL;

public class WebViewPage extends JBasicWebViewAt {
    public static void start(Activity activity, final String url){
        Intent intent = new Intent(activity, WebViewPage.class);
        intent.putExtra(INTENT_URL, url);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected void refreshTitleBar(JTitleBar titleBar){
//        titleBar.replaceRightView();
//        titleBar.getRightIcon().setImageResource(R.drawable.icon_btn_collect_lblock);
    }
}