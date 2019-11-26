package com.jeven.sample.ui.jetpack

import androidx.lifecycle.MutableLiveData

/**
 * 创建人: Jeven
 * 邮箱:   Osbornjie@163.cn
 * 功能:
 */
class LiveBusManage {

    private var bus = mutableMapOf<String, MutableLiveData<Any>>()

    companion object{
        fun getInstance() = Helper.instance
    }

    private object Helper{
        val instance = LiveBusManage()
    }

    @Suppress("UNUSED_PARAMETER")
    fun <T> getChannel(target: String, type: Class<T>): MutableLiveData<T> {
        if (!bus.containsKey(target)) {
            bus[target] = MutableLiveData()
        }
        return bus[target] as MutableLiveData<T>
    }

    fun getChannel(target: String): MutableLiveData<Any> {
        return getChannel(target, Any::class.java)
    }


}
