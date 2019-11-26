package com.jeven.sample.ui.jetpack

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.jeven.sample.R
import com.jeven.sample.ui.BaseFragment
import com.jeven.sample.utils.KeepPagerAdapter
import com.jeven.sample.utils.ToastUtils
import kotlinx.android.synthetic.main.test_live_data_fragment.*

class TestLiveDataFragment : BaseFragment() {

    companion object {
        fun newInstance() = TestLiveDataFragment()
    }

    private lateinit var viewModel: TestLiveDataViewModel
    private var fragments = mutableListOf<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_live_data_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TestLiveDataViewModel::class.java)
        fragments.add(TestOneFragment())
        fragments.add(TestTwoFragment())
        container.adapter = KeepPagerAdapter(childFragmentManager, fragments)


        viewModel.windowMsg.observe(this, Observer {
            ToastUtils.showToast("测试消息$it")
        })

        /*LiveBusManage.getInstance().getChannel("test").observe(this, Observer { ss ->
            ToastUtils.showToast(ss.toString())
        })*/

        LiveDataBus.get().with("busEvent").observe(this, Observer {
            ToastUtils.showToast(it.toString())
        })

        testBtn1.setOnClickListener { viewModel.toastOfWin("我来了") }
        testBtn2.setOnClickListener { viewModel.sendEvent("通信fragment") }
        /*testBtn3.setOnClickListener {
            val dialog =  AlertDialog.Builder(getParent())
                .setMessage("测试生命周期")
                .setCancelable(false)
                .setNegativeButton("取消") { dialog, _ ->  dialog.dismiss()}
                .setPositiveButton("确定", null)
                .create()
            dialog.show()
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setOnClickListener { LiveBusManage.getInstance().updateState() }
        }*/
    }

}
