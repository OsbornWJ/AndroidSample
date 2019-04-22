package com.jeven.sample.utils.bluetooth

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class BlueToothHelper private constructor(private var context: Context){

    private val TAG = "BlueToothHelper"

    private var mBlueToothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var mScanDeviceHelper: ScanDeviceHelper? = null
    private var mDeviceAddedListener: DeviceAddedListener? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        var instance: BlueToothHelper ? = null

        fun getInstance(context: Activity): BlueToothHelper {
            if (instance == null) {
                synchronized(BlueToothHelper ::class) {
                    if (instance == null) {
                        instance = BlueToothHelper(context)
                        instance!!.initScanDeviceHelper()
                    }
                }
            }
            return instance!!
        }
    }

    private fun isSupportBlue(): Boolean {
        return mBlueToothAdapter != null
    }

    private fun isEnableBlue(): Boolean {
        return isSupportBlue() && mBlueToothAdapter.isEnabled
    }

    /**
     * 开启蓝牙（异步）
     */
    private fun openBlueAsyn() {
        if (isSupportBlue()) mBlueToothAdapter.enable()
    }

    /**
     * 开启蓝牙（同步）
     * 会提示是否开启蓝牙，需要在activityResult中判断
     */
    private fun openBlueSync(activity: Activity, requestCode: Int) {
        activity.startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), requestCode)
    }

    private fun initScanDeviceHelper() {
        if (!isEnableBlue()) {
            openBlueAsyn()
        }
        mScanDeviceHelper = ScanDeviceHelper(context, object : ScanDeviceHelper.ScanCallBack {

            override fun onScanFinished() {

            }

            override fun onScaning(device: BluetoothDevice) {
                mDeviceAddedListener?.onAddedDevice(device)
            }

            override fun onScanStarted() {

            }

        })
    }

    fun scanDevice(deviceAddedListener: DeviceAddedListener) {
        if (!isSupportBlue()) return
        this.mDeviceAddedListener = deviceAddedListener
        this.mScanDeviceHelper?.scanDevice()
    }

    fun stopScan() {
        if (!isSupportBlue()) return
        this.mScanDeviceHelper?.stopScan()
    }


    interface DeviceAddedListener {
        fun onAddedDevice(device: BluetoothDevice)
    }

}
