package com.jeven.sample.ui

import androidx.fragment.app.Fragment

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
open class BaseFragment: Fragment() {

    fun getParent(): MainActivity = activity as MainActivity

}