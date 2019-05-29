package com.jeven.sample.utils.bluetooth

import android.bluetooth.BluetoothDevice

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
