package com.jeven.sample.ui.anim

import android.content.Context
import android.util.AttributeSet
import android.widget.Scroller
import android.widget.TextView


/**
 * 创建人: Jeven
 * 邮箱:   Osbornjie@163.cn
 * 功能:
 */
class AnimationTextView : TextView {

    private var mScroller: Scroller? = null

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    init {
         mScroller = Scroller(context)
    }

    fun smoothScrollTo(fx: Int, fy: Int) {
        val dx = fx - mScroller!!.finalX
        val dy = fx - mScroller!!.finalY
        smoothScrollBy(dx, dy)
    }

    private fun smoothScrollBy(dx: Int, dy: Int) {
        //设置mScroller的滚动偏移量
        mScroller!!.startScroll(mScroller!!.finalX, mScroller!!.finalY, dx, dy,4000)
        invalidate()
    }

    override fun computeScroll() {
        if (mScroller!!.computeScrollOffset()) {
            scrollTo(mScroller!!.currX, mScroller!!.currY)
            postInvalidate()
        }
        super.computeScroll()
    }

}