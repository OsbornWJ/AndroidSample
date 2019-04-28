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
class BluetoothHelper private constructor(context: Context) {

    private val DEVICE_HAS_NOT_BLUETOOTH_MODULE = "device has not bluetooth module!"

    private var mBluetoothAdapter: BluetoothAdapter? = null

    private var mOnSearchDeviceListener: OnSearchDeviceListener? = null

    private var mContext: Context? = null

    init {
        mContext = context.applicationContext
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    }

    @Volatile
    private var mReceiver: Receiver? = Receiver()

    companion object {

        @Volatile
        private var sBtHelperClient: BluetoothHelper? = null

        fun from(context: Context): BluetoothHelper? {
            if (sBtHelperClient == null) {
                synchronized(BluetoothHelper::class) {
                    if (sBtHelperClient == null) {
                        sBtHelperClient = BluetoothHelper(context)
                    }
                }
            }
            return sBtHelperClient
        }
    }

    fun searchDevice(onSearchDeviceListener: OnSearchDeviceListener) {
        this.mOnSearchDeviceListener = onSearchDeviceListener
        if (mBluetoothAdapter == null) {
            mOnSearchDeviceListener!!.onError(NullPointerException(DEVICE_HAS_NOT_BLUETOOTH_MODULE))
            return
        }

        if (mReceiver == null) mReceiver = Receiver()

        // ACTION_FOUND
        var filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        mContext!!.registerReceiver(mReceiver, filter)

        // ACTION_DISCOVERY_FINISHED
        filter = IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        mContext!!.registerReceiver(mReceiver, filter)

        if (mBluetoothAdapter!!.isDiscovering)
            mBluetoothAdapter!!.cancelDiscovery()
        mBluetoothAdapter!!.startDiscovery()
    }

    fun close() {
        mBluetoothAdapter!!.cancelDiscovery()
        if (mReceiver != null) {
            mContext!!.unregisterReceiver(mReceiver)
        }
    }

    private inner class Receiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action

            if (BluetoothDevice.ACTION_FOUND == action) {
                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)

                mOnSearchDeviceListener!!.onNewDeviceFound(device)

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
                //mOnSearchDeviceListener!!.onSearchCompleted(mBondedList, mNewList)
            }
        }
    }

}
