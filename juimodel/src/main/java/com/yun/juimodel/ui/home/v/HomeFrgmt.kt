package com.yun.juimodel.ui.home.v

import com.blueprint.basic.frgmt.JAbsListFrgmt
import com.yun.juimodel.ui.home.vm.HomeViewModel

/**
 * @another 江祖赟
 * @date 2018/1/16.
 */
class HomeFrgmt : JAbsListFrgmt<HomeViewModel>() {

    companion object {

    }

    override fun initViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun toSubscribeData(viewModel: HomeViewModel?) {
        super.toSubscribeData(viewModel)
        mViewModel.toSubscribeData(hashMapOf<String, Any>())
    }

    override fun firstUserVisibile() {
        super.firstUserVisibile()
//        mViewModel.toSubscribeData(null)
    }
}