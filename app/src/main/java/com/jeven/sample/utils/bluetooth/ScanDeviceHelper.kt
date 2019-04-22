package com.jeven.sample.utils.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class ScanDeviceHelper(var context: Context, var callback: ScanCallBack?) {

    private var mBlueToothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var scanReceiver: BroadcastReceiver? = null

    private fun scanBlue(): Boolean {
        if (mBlueToothAdapter.isDiscovering) {
            mBlueToothAdapter.cancelDiscovery()
        }
        return mBlueToothAdapter.startDiscovery()
    }

    fun cancelBlue() {
        mBlueToothAdapter.cancelDiscovery()
    }

    /**
     * 开启扫描
     */
    fun scanDevice() {
        this.scanReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val action = intent.action
                when (action) {
                    BluetoothDevice.ACTION_FOUND -> {
                        // Get the BluetoothDevice object from the Intent
                        val device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE) as BluetoothDevice
                        // If it's already paired, skip it, because it's been listed already
                        if (device.bondState != BluetoothDevice.BOND_BONDED) {
                            callback?.onScaning(device)
                        }
                    }
                    BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                        callback?.onScanFinished()
                    }
                }
            }
        }
        // Register for broadcasts when a device is discovered
        var filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        this.context.registerReceiver(this.scanReceiver, filter)

        // Register for broadcasts when discovery has finished
        filter = IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        this.context.registerReceiver(this.scanReceiver, filter)
        scanBlue()
    }

    /**
     * 停止扫描
     */
    fun stopScan() {
        if (this.scanReceiver != null) {
            this.context.unregisterReceiver(this.scanReceiver)
            this.scanReceiver = null
        }
        cancelBlue()
    }

    interface ScanCallBack {
        fun onScanStarted()
        fun onScanFinished()
        fun onScaning(device: BluetoothDevice)
    }

}