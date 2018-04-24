package com.yun.juimodel.ui.home.v.gamedetail

import com.blueprint.basic.frgmt.JAbsListFrgmt
import com.yun.juimodel.ui.home.vm.gamedetail.CommentViewModel

/**
 * @another 江祖赟
 * @date 2018/1/17.
 */
class CommentFrgmt : JAbsListFrgmt<CommentViewModel>() {
    override fun initViewModel(): Class<CommentViewModel> {
        return CommentViewModel::class.java
    }

    override fun toSubscribeData(viewModel: CommentViewModel?) {
        firstUserVisibile()
    }

    override fun firstUserVisibile() {
        mViewModel.toSubscribeData(hashMapOf<String,Any>())
    }
}