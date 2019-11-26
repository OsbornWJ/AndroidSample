package com.jeven.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.jeven.sample.R
import com.jeven.sample.ui.adapter.BaseAdapter
import com.jeven.sample.ui.adapter.BaseViewHolder
import com.jeven.sample.ui.jetpack.LiveBusManage
import com.jeven.sample.utils.ToastUtils
import kotlinx.android.synthetic.main.comm_recyclerview.*

class MainFragment : BaseFragment() {

    private val data = arrayListOf("蓝牙模块", "数据库测试", "布局测试", "动画测试", "LiveData测试")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.comm_recyclerview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewData.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        viewData.addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context, VERTICAL))
        viewData.adapter = object: BaseAdapter<String>(data, android.R.layout.simple_list_item_1) {
            override fun binfMultiViewHolder(viewHolder: BaseViewHolder, item: String) {
                viewHolder.setText(android.R.id.text1, item)
                    .setBackgroundColor(android.R.id.text1, 0xFFFAF0E6.toInt())
                when (item) {
                    "蓝牙模块"      -> {
                        viewHolder.getView<View>(android.R.id.text1).setOnClickListener { it.findNavController().navigate(R.id.action_mainFragment_to_blueToothSampleFragment) }
                    }
                    "数据库测试"    -> {
                        viewHolder.getView<View>(android.R.id.text1).setOnClickListener { it.findNavController().navigate(R.id.action_mainFragment_to_sqliteTestFragment) }
                    }
                    "布局测试"      -> {
                        viewHolder.getView<View>(android.R.id.text1).setOnClickListener { it.findNavController().navigate(R.id.action_mainFragment_to_layoutSampleFragment) }
                    }
                    "动画测试"      -> {
                        viewHolder.getView<View>(android.R.id.text1).setOnClickListener { it.findNavController().navigate(R.id.action_mainFragment_to_animationFragment) }
                    }
                    "LiveData测试" -> {
                        viewHolder.getView<View>(android.R.id.text1).setOnClickListener { it.findNavController().navigate(R.id.action_mainFragment_to_testLiveDataFragment) }
                    }
                }
            }
        }
    }
}
