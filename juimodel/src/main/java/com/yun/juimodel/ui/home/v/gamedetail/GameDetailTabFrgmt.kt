package com.yun.juimodel.ui.home.v.gamedetail

import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.util.SparseArray
import april.yun.other.JTabStyleBuilder.STYLE_ROUND
import com.blueprint.LibApp.findColor
import com.blueprint.LibApp.findDimens
import com.blueprint.adapter.frgmt.BaseFrgmtFractory
import com.blueprint.basic.frgmt.JBaseTabVpFrgmt
import com.blueprint.helper.DpHelper.dp2px2
import com.yun.juimodel.R

/**
 * @another 江祖赟
 * @date 2018/1/17.
 */
class GameDetailTabFrgmt : JBaseTabVpFrgmt() {

    override fun setFrgmtProvider(): BaseFrgmtFractory {
        return object : BaseFrgmtFractory() {
            override fun initFrgment(frgmtCache: SparseArray<Fragment>) {
                frgmtCache.put(0, GameDetailFrgmt())
                frgmtCache.put(1, CommentFrgmt())
            }
        }
    }

    override fun initTabStrip() {
        val tabStyleDelegate = mTabStrip.getTabStyleDelegate()
        mTabStrip.getLayoutParams().height = dp2px2(26f)
        //        2，拿TabStyleDelegate
        //        3, 用TabStyleDelegate设置属性
        tabStyleDelegate.setShouldExpand(true).setNeedTabTextColorScrollUpdate(true)
                //也可以直接传字符串的颜色，第一个颜色表示checked状态的颜色第二个表示normal状态
                .setTextColor(findColor(R.color.j_white), findColor(R.color.gray_8b8b))
                .setTabTextSize(findDimens(R.dimen.spacing_14) as Int)
                .setIndicatorHeight(dp2px2(26f))
                .setFrameColor(findColor(R.color.gray_8b8b))
                .setJTabStyle(STYLE_ROUND)
                .setCornerRadio(dp2px2(5f))
                .setIndicatorColor(findColor(R.color.gray_8b8b))
        val layoutParams = mTabStrip.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.leftMargin = dp2px2(16f)
        layoutParams.rightMargin = dp2px2(16f)
    }

    override fun setTabTitles(): Array<String> {
        return arrayOf("详情", "评价")
    }
}