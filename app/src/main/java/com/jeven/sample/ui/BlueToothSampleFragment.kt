package com.jeven.sample.ui

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeven.sample.R
import com.jeven.sample.ui.adapter.BaseAdapter
import com.jeven.sample.ui.adapter.BaseViewHolder
import com.jeven.sample.utils.bluetooth.BluetoothHelper
import com.jeven.sample.utils.bluetooth.OnSearchDeviceListener
import kotlinx.android.synthetic.main.blue_touch_fragment.*
import kotlinx.android.synthetic.main.comm_recyclerview.*

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class BlueToothSampleFragment : BaseFragment() {

    var mData: MutableList<BluetoothDevice> = mutableListOf()
    var mBondedData: MutableList<BluetoothDevice> = mutableListOf()
    private var blueToothHelper: BluetoothHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.blue_touch_fragment, container, false)
    }

    override fun onDestroy() {
        blueToothHelper!!.close()
        super.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getParent().enableGPSWithPermissionCheck()
        blueToothHelper = BluetoothHelper.from(this.context!!)

        viewData.layoutManager = LinearLayoutManager(context)
        viewData.addItemDecoration(DividerItemDecoration(context,
            DividerItemDecoration.VERTICAL
        ))
        viewData.adapter = object: BaseAdapter<BluetoothDevice>(mData, android.R.layout.simple_list_item_1) {
            override fun binfMultiViewHolder(viewHolder: BaseViewHolder, item: BluetoothDevice) {
                viewHolder.setText(android.R.id.text1, item?.let { it.name+"  "+it.address })
                    .setBackgroundColor(android.R.id.text1, 0xFFFAF0E6.toInt())
                viewHolder.getView<View>(android.R.id.text1).setOnClickListener { blueToothHelper?.bindDevice(item) }
            }
        }
        bindedDevice.layoutManager = LinearLayoutManager(context)
        bindedDevice.addItemDecoration(DividerItemDecoration(context,
            DividerItemDecoration.VERTICAL
        ))
        bindedDevice.adapter = object: BaseAdapter<BluetoothDevice>(mBondedData, android.R.layout.simple_list_item_1) {
            override fun binfMultiViewHolder(viewHolder: BaseViewHolder, item: BluetoothDevice) {
                viewHolder.setText(android.R.id.text1, item?.let { it.name+"  "+it.address })
            }
        }
        blueToothHelper!!.searchDevice(object : OnSearchDeviceListener {
            override fun onNewDeviceFound(device: BluetoothDevice) {
                if (device.bondState == BluetoothDevice.BOND_NONE) {
                    mData.add(device)
                    viewData.adapter!!.notifyDataSetChanged()
                } else if (device.bondState == BluetoothDevice.BOND_BONDED) {
                    mBondedData.add(device)
                    bindedDevice.adapter!!.notifyDataSetChanged()
                }

            }

            override fun onError(e: Exception) {

            }

            override fun onStartDiscovery() {

            }

        })
    }

    override fun onResume() {
        super.onResume()
    }

}
