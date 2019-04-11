package com.jeven.sample.ui.socketsample

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jeven.sample.R

class SocketSampleFragment : Fragment() {

    companion object {
        fun newInstance() = SocketSampleFragment()
    }

    private lateinit var viewModel: SocketSampleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.socket_sample_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SocketSampleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
