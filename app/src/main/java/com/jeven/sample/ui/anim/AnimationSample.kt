package com.jeven.sample.ui.anim

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import com.jeven.sample.R
import com.jeven.sample.ui.BaseFragment
import com.jeven.sample.ui.jetpack.LiveBusManage
import kotlinx.android.synthetic.main.sample_anim_layout.*


/**
 * 创建人: Jeven
 * 邮箱:   Osbornjie@163.cn
 * 功能:
 */
class AnimationSample : BaseFragment(), View.OnClickListener {

/*    lateinit var animationTest: AnimationSet
    lateinit var animatorSet: AnimatorSet*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sample_anim_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_start.setOnClickListener(this)
        btn_start2.setOnClickListener(this)
        testAnimal.setOnClickListener(this)
        animView.visibility = View.GONE

      /*  animationTest = AnimationSet(true)
        animationTest.interpolator = LinearInterpolator()
        val animation1 = TranslateAnimation(0f, 0f, 0f, -300f)
        animation1.duration = 4500
        animation1.repeatCount = 2
        val animation2 = ScaleAnimation(1f, 1.5f, 1f, 1.5f, ScaleAnimation.RELATIVE_TO_SELF, 0f, ScaleAnimation.RELATIVE_TO_SELF, 0f)
        animation2.duration = 4500
        val animation3 = RotateAnimation(0f, 360f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)
        animation3.duration = 4500
        val animation4 = AlphaAnimation(1f, 0.4f)
        animation4.duration = 1000
        animationTest.addAnimation(animation1)
        animationTest.addAnimation(animation2)
        animationTest.addAnimation(animation3)
        animationTest.addAnimation(animation4)
        animationTest.duration = 1000
        animationTest.fillAfter = true
        animationTest.repeatMode = Animation.REVERSE
        animationTest.interpolator = LinearInterpolator()*/


       /* animatorSet = AnimatorSet()

        animator1.duration = 1000
        animatorSet.play(animator1)*/

/*        animatorSet = AnimatorSet()
        val animator1 = ObjectAnimator.ofInt(ViewWrapper(btn_start2), "width", 120, 420)
        val animator3 = ValueAnimator.ofFloat(0f, 1.5f)
        animator3.addUpdateListener {
            val data = it.animatedValue as Float
            testAnimal.scaleX = data
            testAnimal.scaleY = data
        }
        animatorSet.duration = 1000
        animatorSet.play(animator1).after(animator3)*/

    }

    var i: Int = 0
    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_start) {
            /*i++
            animView.smoothScrollTo(10 * i, 10 * i)
            testAnimal.startAnimation(animationTest)*/
        } else {
            val animator1 = ObjectAnimator.ofInt(ViewWrapper(btn_start2), "width", 120, 420)
            animator1.duration = 1000
            animator1.start()
        }
    }

    private inner class ViewWrapper(val target: View) {
        fun getWidth(): Int {
            return target.layoutParams.width
        }
        fun setWidth(width: Int) {
            target.layoutParams.width = width
            target.requestLayout()
        }
    }


}