package com.yun.juimodel.ui.home.vm.gamedetail

import com.yun.juimodel.BR
import com.yun.juimodel.R
import com.yun.juimodel.data.PageDataWrapper
import com.yun.juimodel.ui.home.m.Comment
import com.yun.juimodel.ui.home.m.GameDetail
import com.yun.juimodel.ui.home.m.GameInfo
import com.yun.juimodel.ui.home.v.gamedetail.GameDetailAct
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import jzy.easybindpagelist.loadmorehelper.LoadMoreObjectViewModel
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @another 江祖赟
 * @date 2018/1/17.
 */
class CommentViewModel : LoadMoreObjectViewModel() {

    //拿到接口数据之后 刷新到 commentPageData
    val commentPageData = PageDataWrapper<Any>()
    var mGameDataListener: GameDetailAct.GetGameDataListener? = null

    /**
     * 请使用[这个方法][toGetData]
     */
    override fun registItemTypes(multipleItems: OnItemBindClass<Any>) {
        multipleItems.regist(Comment.CommentBean::class.java, BR.commentModel, R.layout.item_gamedetail_comment)
                .regist(Comment.ScoreListBean::class.java, BR.commenHead, R.layout.item_gamedetail_comment_head)
    }

    override fun toGetData(mapParam: HashMap<String, Any>) {
        collectDisposables(Observable.just(0).delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ _ ->
            if (isFirstPage) {
//                val numberRegex = "\\d+".toRegex()
//                val isNumber = numberRegex::matches
//                val number = isNumber("45")
//                val listOf = listOf<Int>(2, 2, 3, 4)
//                fun che(t: Int): Boolean {
//                    return t > 4
//                }
//
//                val che = che(6)
//                listOf.filter(fun(item): Boolean { return item > 0 })



                commentPageData.list.clear()
                //我的评论item
                commentPageData.list.add(Comment.ScoreListBean())
                //其他评论数据
                val commentBeans = ArrayList<Any>()
                for (i in 0..9) {
                    commentBeans.add(Comment.CommentBean())
                }
                commentPageData.list.addAll(commentBeans)
                //是否有下一页数据 存储
                commentPageData.hasNext = (true)
                afterGetDataSucceed(commentPageData)
                val gameDetail = GameDetail()
                gameDetail.info = GameInfo()
                gameDetail.detail = GameDetail.DetailBean()
                if (mGameDataListener != null) {
                    mGameDataListener?.afterGetData(gameDetail);
                }
            } else {
                //非第一页 数据主关注 其他评论
                //直接使用 接口的 pageDataWrapper数据
                val no1CommentPageData = PageDataWrapper<Any>()
                val commentBeans = ArrayList<Any>()
                for (i in dataLists.size until dataLists.size + 10) {
                    commentBeans.add(Comment.CommentBean())
                }
                no1CommentPageData.list.addAll(commentBeans)
                afterGetDataSucceed(no1CommentPageData)
            }
        }))
    }

    private fun afterGetDataSucceed(commentPageData: PageDataWrapper<Any>) {
        if (isFirstPage) {
            refreshedAllData(commentPageData.list)
        } else {
            addMoreData(commentPageData.list, commentPageData.hasNext)
        }
    }

    fun setOnGameDataGetListener(gameDataListener: GameDetailAct.GetGameDataListener) {
        mGameDataListener = gameDataListener
    }
}