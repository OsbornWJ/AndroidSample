package com.jeven.sample.ui.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jeven.sample.R
import com.jeven.sample.ui.BaseFragment
import kotlinx.android.synthetic.main.socket_sample_fragment.*

/**
 * 创建人: Jeven
 * 邮箱:   Osbornjie@163.cn
 * 功能:
 */
class TestOneFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.socket_sample_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        message.text = "我是测试消息 1111"
      /*  val viewModel = ViewModelProviders.of(activity!!).get(TestLiveDataViewModel::class.java)
        viewModel.fragmentMsg.value = "one to two"*/
    }

}