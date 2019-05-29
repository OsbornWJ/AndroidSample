package com.jeven.sample

import android.app.Application
import com.jeven.sample.utils.AppUtil

/**
 * 创建人: Jeven
 * 功能:
 */
class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppUtil.init(this)
    }

}