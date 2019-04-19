package com.jeven.sample.utils.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jeven.sample.utils.AppUtil
import com.jeven.sample.utils.SampleLogUtil

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class BlueToothHelper {

    private val TAG = "BlueToothHelper"

    private var mBlueToothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var mScanResultReciver: ScanResultReciver? = null

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
    fun openBlueSync(activity: AppCompatActivity, requestCode: Int) {
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

    /**
     * 扫描蓝牙 返回
     */
    private class ScanResultReciver(val scanCallBack: ScanCallBack): BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent!!.action
            val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
            when(action) {
                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                    SampleLogUtil.logI("开始扫描")
                    scanCallBack.onScanStarted()
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    SampleLogUtil.logI("结束扫描")
                    scanCallBack.onScanFinished()
                }
                BluetoothDevice.ACTION_FOUND -> {
                    SampleLogUtil.logI("发现设备")
                    scanCallBack.onScaning(device)
                }
            }
        }
    }

    /**
     * 查找蓝牙
     */
    fun findBlueTooth(scanCallBack: ScanCallBack) {
        val filter = IntentFilter()
        filter.addAction("android.bluetooth.device.action.ACTION_DISCOVERY_STARTED")
        filter.addAction("android.bluetooth.device.action.FOUND")
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED")
        if (this.mScanResultReciver != null) {
            AppUtil.getApplication().unregisterReceiver(this.mScanResultReciver)
            this.mScanResultReciver = null
        }
        this.mScanResultReciver = ScanResultReciver(scanCallBack)
        AppUtil.getApplication().registerReceiver(ScanResultReciver(scanCallBack), filter)
        scanBlue()
    }

    /**
     * 退出蓝牙搜索
     */
    fun destoryScan() {
        if (this.mScanResultReciver != null) {
            AppUtil.getApplication().unregisterReceiver(this.mScanResultReciver)
            this.mScanResultReciver = null
        }
        cancelBlue();
    }

    interface ScanCallBack {
        fun onScanStarted()
        fun onScanFinished()
        fun onScaning(device: BluetoothDevice)
    }

}
