package com.jeven.sample.utils

import android.util.Log
import com.jeven.sample.BuildConfig

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
object SampleLogUtil {

    private const val TAG: String = "sample"

    fun logW(msg: String) {
        if (BuildConfig.DEBUG) Log.w(TAG, msg)
    }

    fun logD(msg: String) {
        if (BuildConfig.DEBUG) Log.d(TAG, msg)
    }

    fun logI(msg: String) {
        if (BuildConfig.DEBUG) Log.i(TAG, msg)
    }

    fun logE(msg: String) {
        if (BuildConfig.DEBUG) Log.e(TAG, msg)
    }

    fun logE(msg: String, tr: Throwable) {
        if (BuildConfig.DEBUG) Log.e(TAG, msg, tr)
    }



}
