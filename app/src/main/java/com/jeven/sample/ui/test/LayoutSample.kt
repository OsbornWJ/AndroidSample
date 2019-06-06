package com.jeven.sample.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.jeven.sample.R
import com.jeven.sample.ui.BaseFragment
import kotlinx.android.synthetic.main.sample_layout_view1.*



/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:   ConstraintLayout动画
 */
class LayoutSample : BaseFragment(), View.OnClickListener {

    var mConstraintSet1: ConstraintSet? = null
    var mConstraintSet2: ConstraintSet? = null
    var mConstraintSets: ConstraintSet? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sample_layout_view1, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mConstraintSet1 = ConstraintSet()
        mConstraintSet2 = ConstraintSet()

        //把默认 constraintLayout 布局放到 mConstraintSet1 中
        mConstraintSet1!!.clone(root_layout)
        //把标定位置变换的 constraintLayout 布局放到 mConstraintSet2 中
        mConstraintSet2!!.clone(context, R.layout.sample_layout_view2)

        btn_start.setOnClickListener(this)
        btn_reset.setOnClickListener(this)
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_start -> {
                TransitionManager.beginDelayedTransition(root_layout)
                mConstraintSet2!!.applyTo(root_layout)
            }
            R.id.btn_reset -> {
                val transition = AutoTransition()
                transition.duration = 500
                TransitionManager.beginDelayedTransition(root_layout, transition)
                mConstraintSet1!!.applyTo(root_layout)
            }
        }
    }
}