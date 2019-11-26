package com.jeven.sample.ui.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel


class TestLiveDataViewModel : ViewModel() {

    var windowMsg = MutableLiveData<String>()
    var fragmentMsg = MutableLiveData<String>()

    fun toastOfWin(msg: String) {
        windowMsg.value = msg
    }

    fun sendEvent(event: String) {
        fragmentMsg.postValue(event)
    }

    var billCodeInput = MutableLiveData<String>()

    var orderInfo: LiveData<OrderInfo> = Transformations.switchMap(billCodeInput) { id -> getOrderInfo(id) }

    fun setInput(billCode: String) {
        billCodeInput.value = billCode
    }

    private fun getOrderInfo(input: String): LiveData<OrderInfo> {
        val orderInfo = MutableLiveData<OrderInfo>()
        orderInfo.value = OrderInfo("test")
        return orderInfo
    }

}

data class OrderInfo(var id: String)
