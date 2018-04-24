package com.yun.juimodel.ui.home.v.gamedetail

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.yun.juimodel.R

class Game2CommentAct : AppCompatActivity() {

    companion object {
        var sContent: String? = null
        var sScore: Float = 0.toFloat()
        var sGameID: String? = null
        @JvmStatic
        fun startAct4View(view: View, content: String, score: Float, gameID: String) {
            //todo 前提：需玩过或预约或已安装该游戏方可参与评价。
            sContent = content
            sScore = score
            sGameID = gameID
            var context = view.context
            while (context is ContextWrapper) {
                if (context is Activity) {
                    val activity = context
                    val intent = Intent(activity, Game2CommentAct::class.java)
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
        setContentView(R.layout.activity_game2_comment)
    }
}
