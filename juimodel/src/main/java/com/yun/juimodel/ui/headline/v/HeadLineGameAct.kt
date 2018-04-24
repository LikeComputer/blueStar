package com.yun.juimodel.ui.headline.v

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.yun.juimodel.R

class HeadLineGameAct : AppCompatActivity() {
    companion object {
        val GAME_ID = "game_id"
        val GAME_NAME = "game_name"
        // java调用kotlin 的静态方法需要加上注解 @JvmStatic
//    @JvmStatic
        fun startAct4View(view: View, gameName: String, gameId: String) {
            var context = view.context
            while (context is ContextWrapper) {
                if (context is Activity) {
                    val activity = context
                    val intent = Intent(activity, HeadLineGameAct::class.java)
                    intent.putExtra(GAME_ID, gameId)
                    intent.putExtra(GAME_NAME, gameName)
                    activity.startActivity(intent)
                    //ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
                }
                context = context.baseContext
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_head_line_game)
    }
}
