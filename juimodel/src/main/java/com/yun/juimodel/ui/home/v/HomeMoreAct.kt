package com.yun.juimodel.ui.home.v

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.yun.juimodel.R

class HomeMoreAct : AppCompatActivity() {

    companion object {
        @JvmField
        var MORE_TYPE = "首页更多类型"
        @JvmField
        var HOME_MORE_WEALFARE = "福利中心"
        @JvmField
        var HOME_MORE_GAMERES = "游戏资源"
        @JvmField
        var HOME_MORE_USERRECOM = "玩家推荐"

        @JvmStatic
        fun startAct4View(view: View, moreType: String) {
            var context = view.context
            while (context is ContextWrapper) {
                if (context is Activity) {
                    val activity = context
                    val intent = Intent(activity, HomeMoreAct::class.java)
                    intent.putExtra(MORE_TYPE, moreType)
                    activity.startActivity(intent)
                    //ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
                }
                context = context.baseContext
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_more)
    }
}
