package com.jeven.sample.ui.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * 创建人: Jeven
 * 邮箱:   liaowenjie@sto.cn
 * 功能:
 */
abstract class BaseAdapter<T>() : RecyclerView.Adapter<BaseViewHolder>() {

    private var mData: MutableList<T> = mutableListOf()
    @LayoutRes var layoutId: Int = 0

    constructor(layoutId: Int) : this() {
        this.layoutId = layoutId
    }

    constructor(data: MutableList<T>, layoutId: Int) : this() {
        this.mData = data
        this.layoutId = layoutId
    }

    fun addData(data: T) {
        mData.add(data)
        this.notifyDataSetChanged()
    }

    fun addDatas(datas: MutableList<T>) {
        mData.addAll(datas)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder {
        return createMultiViewHolder(viewGroup, i)
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder, i: Int) = binfMultiViewHolder(viewHolder, mData[i])

    override fun getItemCount(): Int {
        return mData.size
    }

    fun createMultiViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(layoutId, viewGroup, false)
        return BaseViewHolder(view, this)
    }

    abstract fun binfMultiViewHolder(viewHolder: BaseViewHolder, item: T)
}
