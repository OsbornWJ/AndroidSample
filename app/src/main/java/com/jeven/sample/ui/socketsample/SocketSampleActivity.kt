package com.jeven.sample.ui.socketsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jeven.sample.R

class SocketSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.socket_sample_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SocketSampleFragment.newInstance())
                .commitNow()
        }
    }

}
