package com.jeven.sample.utils

import android.annotation.SuppressLint
import android.app.Application

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

}