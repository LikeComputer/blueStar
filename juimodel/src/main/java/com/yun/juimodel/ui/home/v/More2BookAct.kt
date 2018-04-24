package com.yun.juimodel.ui.home.v

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.yun.juimodel.R

class More2BookAct : AppCompatActivity() {

    companion object {
        @JvmField
        var HOME_MORE_BOOKGAME = "新游预定"
        @JvmField
        var HOME_MORE_FINDGAME = "发现游戏"
        private var sMoreType = HOME_MORE_BOOKGAME
        @JvmStatic
        fun startAct4View(view: View, moreType: String) {
            sMoreType = moreType
            var context = view.context
            while (context is ContextWrapper) {
                if (context is Activity) {
                    val activity = context
                    val intent = Intent(activity, More2BookAct::class.java)
                    //intent.putExtra(SEARCH_TYPE, search_type);
                    activity.startActivity(intent)
                    //ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
                }
                context = context.baseContext
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more2_book)
    }
}
