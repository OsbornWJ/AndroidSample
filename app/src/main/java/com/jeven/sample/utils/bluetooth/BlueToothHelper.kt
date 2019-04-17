package com.jeven.sample.utils.bluetooth

import android.bluetooth.BluetoothAdapter

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class BlueToothHelper {

    var mBlueToothAdapter = BluetoothAdapter.getDefaultAdapter()

    fun isSupportBlue(): Boolean {
        return mBlueToothAdapter != null
    }

    fun isEnableBlue(): Boolean {
        return isSupportBlue() && mBlueToothAdapter.isEnabled
    }

    fun openBlueTooth() {
        if (isSupportBlue()) mBlueToothAdapter.enable()
    }



}
