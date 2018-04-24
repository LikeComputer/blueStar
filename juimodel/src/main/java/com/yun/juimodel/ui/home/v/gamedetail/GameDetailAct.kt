package com.yun.juimodel.ui.home.v.gamedetail

import android.animation.ArgbEvaluator
import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.blueprint.LibApp
import com.blueprint.basic.activity.JBaseTitleStateActivity
import com.blueprint.helper.ToastHelper
import com.yun.juimodel.BR
import com.yun.juimodel.R
import com.yun.juimodel.databinding.ActivityGameDetailBinding
import com.yun.juimodel.ui.home.m.GameDetail
import com.yun.juimodel.ui.home.vm.gamedetail.GameDetailViewModel
import kotlin.concurrent.thread

class GameDetailAct : JBaseTitleStateActivity<GameDetailViewModel>(), AppBarLayout.OnOffsetChangedListener {

    companion object {
        //"state":"状态：0-预约；1-正常",
        @JvmField
        val GAMESTATE_NORMAL = 1
        @JvmField
        val GAMESTATE_BOOKING = 0
        var gameId: String = ""
        var showComment: Boolean = false
        @JvmStatic
        fun startAct4View(view: View, id: String) {
            startAct4View(view, false, id)
        }

        @JvmStatic
        fun startAct(activity: Activity, id: String) {
            gameId = id
            activity.startActivity(Intent(activity, GameDetailAct::class.java))
        }

        @JvmStatic
        fun startAct4View(view: View, comment: Boolean, id: String) {
            var id = id
            gameId = id
            showComment = comment
            id = "0"
            if (!TextUtils.isEmpty(id)) {
                var context = view.context
                while (context is ContextWrapper) {
                    if (context is Activity) {
                        val activity = context
                        val intent = Intent(activity, GameDetailAct::class.java)
                        //intent.putExtra(SEARCH_TYPE, search_type);
                        activity.startActivity(intent)
                    }
                    context = context.baseContext
                }
            } else {
                ToastHelper.showShort("游戏ID为空")
            }
        }
    }

    private var mTitleView: TextView? = null
    private var mAppbar: AppBarLayout? = null
    private var mTitleDiviceline: View? = null
    private var mVerticalOffset: Int = 0
    var mGameDetailBinding: ActivityGameDetailBinding? = null
    private var mLeftIcon: ImageView? = null
    private var mRigthtIcon: ImageView? = null
    internal var argbEvaluator = ArgbEvaluator()
    private val mDividiColor = LibApp.findColor(R.color.divide_line_color)
    private val mIconColor = LibApp.findColor(R.color.gray_8b8b)

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
        super.onCreate(savedInstanceState)
    }

    override fun initViewModel(): Class<GameDetailViewModel> {
        return GameDetailViewModel::class.java
    }

    override fun toSubscribeData(viewModel: GameDetailViewModel) {
    }

    override fun setContentVariableId(): Int {
        return BR.gameDetailViewModel
    }

    override fun setContentLayoutId(): Int {
        return 0
    }


    override fun setContentLayout4Act(): ViewDataBinding {
        mGameDetailBinding = DataBindingUtil.setContentView<ActivityGameDetailBinding>(this, R.layout.activity_game_detail)
        mAppbar = mGameDetailBinding?.appbar
        mAppbar?.addOnOffsetChangedListener(this)
        mTitleDiviceline = mGameDetailBinding?.titleDiviceline
        thread { ->
            val gameDetailTabFrgmt = GameDetailTabFrgmt()
            supportFragmentManager.beginTransaction().replace(R.id.home_search_content, gameDetailTabFrgmt).commitAllowingStateLoss()
            if (showComment) {
                //默认显示 评论tab
                gameDetailTabFrgmt.setCurrentItem(1)
            }
        }.start()
        return mGameDetailBinding as ViewDataBinding
    }

    override fun findStatbleViews(rootView: View?) {
        super.findStatbleViews(rootView)
    }

    override fun setRootVariableId(): Int {
        return BR.gameDetailViewModel
    }

    override fun initToolBar() {
        super.initToolBar()
        mLeftIcon = mToolBar.setLeftIcon(R.drawable.btn_back_38_white)
        mRigthtIcon = mGameDetailBinding?.rightShareIcon
        mRigthtIcon?.setOnClickListener(View.OnClickListener { ToastHelper.showShort("分享") })
        setTitle("游戏详情")
        mTitleView = mToolBar.setTitle2("游戏详情")
        mTitleView?.setAlpha(0f)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (mVerticalOffset != verticalOffset) {
            mVerticalOffset = verticalOffset
            val alpha = Math.abs(verticalOffset * 1f / appBarLayout.totalScrollRange)
            mTitleView?.alpha = (alpha)
            mTitleDiviceline?.setBackgroundColor(argbEvaluator.evaluate(alpha, Color.TRANSPARENT, mDividiColor) as Int)
            val evaluate = argbEvaluator.evaluate(alpha, Color.TRANSPARENT, mIconColor) as Int
            mLeftIcon?.setColorFilter(evaluate)
            mRigthtIcon?.setColorFilter(evaluate)
        }
    }

    fun getViewModel(): GameDetailViewModel {
        return mViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        mAppbar?.removeOnOffsetChangedListener(this)
    }


    //预约有戏按钮//下载游戏 // 打开游戏
    fun toBookPlay(view: View) {
        ToastHelper.showShort("游戏预约")
    }

    //查看游戏动态
    fun watchGameDynamic(view: View) {
        ToastHelper.showShort("查看动态")
    }


    //关注/取消关注
    fun actLike(view: View) {
        //view.isSelected();
        //view.setSelected();
        ToastHelper.showShort("关注操作")
    }


    interface GetGameDataListener {
        fun afterGetData(gameDetail: GameDetail)
    }
}
