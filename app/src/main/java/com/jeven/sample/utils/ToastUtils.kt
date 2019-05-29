package com.jeven.sample.utils

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

/**
 * 创建人: Jeven
 * 功能:
 */
class ToastUtils {

    companion object {

        private var lastMsg: String = ""

        fun showToast(msg: String) {
            if (lastMsg == msg) return
            Toast.makeText(AppUtil.getApplication(), msg, LENGTH_SHORT).show()
        }
    }

}