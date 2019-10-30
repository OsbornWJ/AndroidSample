package com.jeven.sample.ui.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Matrix
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import com.jeven.sample.R
import com.jeven.sample.ui.BaseFragment
import kotlinx.android.synthetic.main.sample_anim_layout.*


/**
 * 创建人: Jeven
 * 邮箱:   Osbornjie@163.cn
 * 功能:
 */
class AnimationSample : BaseFragment(), View.OnClickListener {

    lateinit var animationTest: AnimationSet
    lateinit var animatorSet: AnimatorSet

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sample_anim_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_start.setOnClickListener(this)
        btn_start2.setOnClickListener(this)
        animView.visibility = View.GONE

        animationTest = AnimationSet(true)
        animationTest.interpolator = LinearInterpolator()
        val animation1 = TranslateAnimation(0f, 0f, 0f, -100f)
        animation1.duration = 500
        val animation2 = ScaleAnimation(1f, 1.5f, 1f, 1.5f, ScaleAnimation.RELATIVE_TO_SELF, 0f, ScaleAnimation.RELATIVE_TO_SELF, 0f)
        animation2.duration = 800
        val animation3 = RotateAnimation(0f, 360f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)
        animation3.duration = 600
        val animation4 = AlphaAnimation(1f, 0.1f)
        animation4.duration = 1200
        animationTest.addAnimation(animation1)
        animationTest.addAnimation(animation2)
        animationTest.addAnimation(animation3)
        animationTest.addAnimation(animation4)
        animationTest.interpolator = LinearInterpolator()

        animatorSet = AnimatorSet()
        val animator1 = ObjectAnimator.ofFloat(testAnimal, "translationY", 0f, -100f)
        animator1.duration = 1000
        animatorSet.play(animator1)


     /*   animatorSet = AnimatorSet()
        val animator1 = ValueAnimator.ofFloat(0f, 1.5f)
        animator1.duration = 1000
        animator1.addUpdateListener {
            val data = it.animatedValue as Float
            testAnimal.scaleX = data
            testAnimal.scaleY = data
        }
        animatorSet.play(animator1)*/

    }

    var i: Int = 0
    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_start) {
            i++
            animView.smoothScrollTo(10 * i, 10 * i)
            testAnimal.startAnimation(animationTest)
        } else {
            animatorSet.start()
        }
    }


}