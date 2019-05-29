package com.jeven.sample.utils.bluetooth

import android.bluetooth.BluetoothDevice

/**
 * 创建人: Jeven
 * 功能:   绑定蓝牙设备回调
 */
interface OnBindDeviceListener : IErrorListener {

    /**
     * 绑定设备 请求
     */
    fun onBindRequest()

    /**
     * 绑定失败
     */
    fun onBindFail(device: BluetoothDevice)

    /**
     * 绑定中
     */
    fun onBinding(device: BluetoothDevice)

    /**
     * 绑定设备成功
     */
    fun onBindSuccess(device: BluetoothDevice)



}