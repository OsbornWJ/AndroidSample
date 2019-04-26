package com.jeven.sample.utils.bluetooth

import android.bluetooth.BluetoothDevice

/**
 * A listener for searching devices.
 * Created by wuhaojie on 2016/9/8 14:50.
 */
interface OnSearchDeviceListener : IErrorListener {
    /**
     * Call before discovery devices.
     */
    fun onStartDiscovery()

    /**
     * Call when found a new device.
     *
     * @param device the new device
     */
    fun onNewDeviceFound(device: BluetoothDevice)

}
