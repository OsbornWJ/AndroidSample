package com.jeven.sample.utils

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
object ViewClickFast {

    private val MIN_DELAY_TIME = 600  // 两次点击间隔不能少于1000ms
    private var lastClickTime: Long = 0

    fun isFastClick(): Boolean {
        var flag = true
        val currentClickTime = System.currentTimeMillis()
        if (currentClickTime - lastClickTime >= MIN_DELAY_TIME) {
            flag = false
        }
        lastClickTime = currentClickTime
        return flag
    }

}