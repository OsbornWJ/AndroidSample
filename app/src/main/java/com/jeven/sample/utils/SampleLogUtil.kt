package com.jeven.sample.utils

import android.util.Log

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
object SampleLogUtil {

    private const val TAG: String = "sample"

    fun logW(msg: String) {
        Log.w(TAG, msg)
    }

    fun logD(msg: String) {
        Log.d(TAG, msg)
    }

    fun logI(msg: String) {
        Log.i(TAG, msg)
    }

    fun logE(msg: String) {
        Log.e(TAG, msg)
    }

    fun logE(msg: String, tr: Throwable) {
        Log.e(TAG, msg, tr)
    }



}
