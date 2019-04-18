package com.jeven.sample.utils.bluetooth

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.util.Log
import com.jeven.sample.ui.MainActivity

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class BlueToothHelper {

    private val TAG = "BlueToothHelper"

    private var mBlueToothAdapter = BluetoothAdapter.getDefaultAdapter()

    fun isSupportBlue(): Boolean {
        return mBlueToothAdapter != null
    }

    fun isEnableBlue(): Boolean {
        return isSupportBlue() && mBlueToothAdapter.isEnabled
    }

    /**
     * 开启蓝牙（异步）
     */
    fun openBlueAsyn() {
        if (isSupportBlue()) mBlueToothAdapter.enable()
    }

    /**
     * 开启蓝牙（同步）
     * 会提示是否开启蓝牙，需要在activityResult中判断
     */
    fun openBlueSync(activity: Activity, requestCode: Int) {
        activity.startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), requestCode)
    }

    /**
     * 开启扫描
     */
    fun scanBlue(): Boolean {
        if (!isSupportBlue() || !isEnableBlue()) {
            Log.e(TAG, "blueTooth not enable")
            return false
        }

        if (mBlueToothAdapter.isDiscovering) {
            mBlueToothAdapter.cancelDiscovery()
        }
        return mBlueToothAdapter.startDiscovery()
    }

    /**
     * 停止扫描
     */
    fun cancelBlue(): Boolean {
        if (isSupportBlue()) {
            mBlueToothAdapter.cancelDiscovery()
        }
        return true
    }



}
