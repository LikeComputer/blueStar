package com.yun.juimodel.data

/**
 * @another 江祖赟
 * @date 2018/1/16.
 */
data class PageDataWrapper<D>(var page: String = "0", var hasNext: Boolean = true, var total: Int = 0, var tip: String? =
null, var list: ArrayList<D> = arrayListOf(), var lastindex: Long = 0)