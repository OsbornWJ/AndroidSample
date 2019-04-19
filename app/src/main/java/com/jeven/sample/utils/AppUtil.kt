package com.jeven.sample.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.LocationManager

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
@SuppressLint("StaticFieldLeak")
object AppUtil {

    private lateinit var mContext: Application

    fun init(context: Application) {
        mContext = context
    }

    fun getApplication(): Application {
        return mContext
    }

    /**
     * 检查GPS是否打开
     * @return
     */
    fun checkGPSIsOpen(): Boolean {
        val locationManager:LocationManager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
    }


}