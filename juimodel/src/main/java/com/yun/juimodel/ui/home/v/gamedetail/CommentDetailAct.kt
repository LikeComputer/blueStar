package com.yun.juimodel.ui.home.v.gamedetail

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.yun.juimodel.R

class CommentDetailAct : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun startAct4View(view: View) {
            var context = view.context
            while (context is ContextWrapper) {
                if (context is Activity) {
                    val activity = context
                    val intent = Intent(activity, CommentDetailAct::class.java)
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
        setContentView(R.layout.activity_comment_detail)
    }
}
