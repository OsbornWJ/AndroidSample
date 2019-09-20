package com.jeven.sample.ui.anim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jeven.sample.R
import com.jeven.sample.ui.BaseFragment
import kotlinx.android.synthetic.main.sample_anim_layout.*

/**
 * 创建人: Jeven
 * 邮箱:   Osbornjie@163.cn
 * 功能:
 */
class AnimationSample : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sample_anim_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_start.setOnClickListener(this)
    }

    var i: Int = 0
    override fun onClick(v: View?) {
        i ++
        animView.smoothScrollTo(10*i, 10*i)
    }


}