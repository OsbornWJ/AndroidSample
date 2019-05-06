package com.jeven.sample.utils.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import com.jeven.sample.utils.SampleLogUtil
import java.lang.reflect.Method

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
object BluetoothUtil {

    private var mBluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    fun isSupportBlue(): Boolean {
        return mBluetoothAdapter != null
    }

    /**
     * 配对蓝牙
     */
    fun pinDeveice(device: BluetoothDevice?) {
        if (device == null) {
            SampleLogUtil.logE("device is null")
            return
        }

        if (!isSupportBlue()) {
            SampleLogUtil.logE("no support bluetooth")
            return
        }

        if (mBluetoothAdapter!!.isDiscovering) {
            mBluetoothAdapter!!.cancelDiscovery()
        }
        // 判断设备是否绑定
        if (device.bondState == BluetoothDevice.BOND_NONE) {
            SampleLogUtil.logD("attemp to bond:" + device.name)
            try {
                val createBondMethod: Method = device.javaClass.getMethod("createBond")
                createBondMethod.invoke(device)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    /**
     * 移除蓝牙配对
     */
    fun removePin(device: BluetoothDevice?) {
        if (device == null) {
            SampleLogUtil.logE("device is null")
            return
        }

        if (!isSupportBlue()) {
            SampleLogUtil.logE("no support bluetooth")
            return
        }
        if (mBluetoothAdapter!!.isDiscovering) {
            mBluetoothAdapter!!.cancelDiscovery()
        }
        // 判断设备是否绑定
        if (device.bondState == BluetoothDevice.BOND_BONDED) {
            SampleLogUtil.logD("attemp to remove:" + device.name)
            try {
                val createBondMethod: Method = device.javaClass.getMethod("removeBond")
                createBondMethod.invoke(device)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
