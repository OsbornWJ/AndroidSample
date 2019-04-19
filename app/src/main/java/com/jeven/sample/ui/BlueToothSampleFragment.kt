package com.jeven.sample.ui

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.jeven.sample.R
import com.jeven.sample.ui.adapter.BaseAdapter
import com.jeven.sample.ui.adapter.BaseViewHolder
import com.jeven.sample.utils.bluetooth.BlueToothHelper
import kotlinx.android.synthetic.main.comm_recyclerview.*

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class BlueToothSampleFragment : BaseFragment(), BlueToothHelper.ScanCallBack {

    var mData: MutableList<BluetoothDevice> = mutableListOf()
    var mBlueToothHelper = BlueToothHelper()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.blue_touch_fragment, container, false)
    }

    override fun onDestroyView() {
        mBlueToothHelper.destoryScan()
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getParent().enableGPS("是否打开GPS")
        mBlueToothHelper.openBlueAsyn()
        mBlueToothHelper.findBlueTooth(this)
        viewData.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        viewData.addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(context,
            DividerItemDecoration.VERTICAL
        ))
        viewData.adapter = object: BaseAdapter<BluetoothDevice>(mData, android.R.layout.simple_list_item_1) {
            override fun binfMultiViewHolder(viewHolder: BaseViewHolder, item: BluetoothDevice) {
                viewHolder.setText(android.R.id.text1, item.name)
                    .setBackgroundColor(android.R.id.text1, 0xFFFAF0E6.toInt())
            }
        }
    }

    override fun onScanStarted() {

    }

    override fun onScanFinished() {

    }

    override fun onScaning(device: BluetoothDevice) {
        mData.add(device)
    }

}
