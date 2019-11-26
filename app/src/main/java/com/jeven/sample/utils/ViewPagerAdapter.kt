package com.jeven.sample.utils

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * 创建人: Jeven
 * 邮箱:   Osbornjie@163.cn
 * 功能:
 */
class KeepPagerAdapter(@NonNull fm: FragmentManager, @NonNull val fragments: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

}