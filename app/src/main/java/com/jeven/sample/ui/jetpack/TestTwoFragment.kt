package com.jeven.sample.ui.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jeven.sample.R
import com.jeven.sample.ui.BaseFragment
import kotlinx.android.synthetic.main.socket_sample_fragment.*

/**
 * 创建人: Jeven
 * 邮箱:   Osbornjie@163.cn
 * 功能:
 */
class TestTwoFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.socket_sample_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        message.text = "我是测试消息 2222"
        val viewModel = ViewModelProviders.of(parentFragment!!).get(TestLiveDataViewModel::class.java)
        viewModel.fragmentMsg.observe(parentFragment!!, Observer { message.text = "121${it}" })
    }

}