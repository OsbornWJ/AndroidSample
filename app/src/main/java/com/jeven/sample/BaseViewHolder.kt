package com.jeven.sample

import android.support.annotation.ColorInt
import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.TextView

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
class BaseViewHolder(val covertVuew: View, var adapter: BaseAdapter<*>): RecyclerView.ViewHolder(covertVuew) {

    /**
     * Views indexed with their IDs
     */
    private val views = SparseArray<View>()

    fun setText(@IdRes viewId: Int, content: CharSequence): BaseViewHolder {
        getView<TextView>(viewId).text = content
        return this
    }

    fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int): BaseViewHolder {
        getView<View>(viewId).setBackgroundColor(color)
        return this
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : View> getView(@IdRes viewId: Int): T {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = covertVuew.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }

}