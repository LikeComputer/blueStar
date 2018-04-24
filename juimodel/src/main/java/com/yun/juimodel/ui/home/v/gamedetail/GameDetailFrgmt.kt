package com.yun.juimodel.ui.home.v.gamedetail

import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import com.blueprint.basic.frgmt.JBaseResetTitleStateFrgmt
import com.yun.Constants
import com.yun.juimodel.databinding.FragmentGameDetailBinding
import com.yun.juimodel.ui.home.m.GameDetail
import com.yun.juimodel.ui.home.m.GameInfo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import jonas.jlayout.MultiStateLayout
import jzy.easybindpagelist.statehelper.SimpleDiffStateViewModel
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @another 江祖赟
 * @date 2018/1/17.
 */
class GameDetailFrgmt : JBaseResetTitleStateFrgmt<SimpleDiffStateViewModel>() {

    override fun initViewModel(): Class<SimpleDiffStateViewModel> {
        return SimpleDiffStateViewModel::class.java
    }

    private var mGameDetailBinding: FragmentGameDetailBinding? = null
    private var mStateLayout: MultiStateLayout? = null

    override fun setContentLayout4Frgmt(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding {
        mGameDetailBinding = FragmentGameDetailBinding.inflate(inflater)
        mStateLayout = mGameDetailBinding?.stateLayout
        return mGameDetailBinding as ViewDataBinding

    }

    fun setGameDetailData(data: GameDetail.DetailBean) {
        mGameDetailBinding?.setGameDetail(data)
        data.gamePics.addAll(Arrays.asList<String>(Constants.Temp.url1, Constants.Temp.url2, Constants.Temp.url3))
        data.recimGames.addAll(Arrays.asList<GameDetail.RecommendBean>(GameDetail.RecommendBean(), GameDetail.RecommendBean(), GameDetail.RecommendBean()))
    }

    override fun firstUserVisibile() {
        collectDisposables(Observable.just(0).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe
        ({ _ ->
            val gameDetail = GameDetail()
            gameDetail.info = GameInfo()
            gameDetail.detail = GameDetail.DetailBean()
            setGameDetailData(gameDetail.detail)
            (mContext as GameDetailAct).getViewModel().showGameDetail(gameDetail.info)
            mStateLayout?.showStateSucceed()
        }))
    }
}