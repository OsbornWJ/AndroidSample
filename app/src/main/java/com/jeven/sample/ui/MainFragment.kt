package com.jeven.sample.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.jeven.sample.BaseAdapter
import com.jeven.sample.BaseViewHolder
import com.jeven.sample.R
import kotlinx.android.synthetic.main.comm_recyclerview.*

class MainFragment : Fragment() {

    private val data = arrayListOf("蓝牙模块")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.comm_recyclerview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewData.layoutManager = LinearLayoutManager(context)
        viewData.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        viewData.adapter = object: BaseAdapter<String>(data, android.R.layout.simple_list_item_1) {
            override fun binfMultiViewHolder(viewHolder: BaseViewHolder, item: String) {
                viewHolder.setText(android.R.id.text1, item)
                    .setBackgroundColor(android.R.id.text1, 0xFFFAF0E6.toInt())
                when (item) {
                    "蓝牙模块" -> {
                        viewHolder.getView<View>(android.R.id.text1).setOnClickListener { it.findNavController().navigate(R.id.action_mainFragment_to_blueTouchSampleFragment) }
                    }
                }
            }
        }
    }
}
