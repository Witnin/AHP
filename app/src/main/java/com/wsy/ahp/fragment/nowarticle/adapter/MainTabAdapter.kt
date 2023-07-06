package com.wsy.ahp.fragment.nowarticle.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wsy.ahp.model.entity.Type


class MainTabAdapter(fm: FragmentManager, fragments:MutableList<Fragment>?, titles: List<Type>) : FragmentPagerAdapter(fm){

    private val fragments: MutableList<Fragment>? = fragments
    private var titles: List<Type> = titles
    override fun getItem(position: Int): Fragment = fragments!![position]
    override fun getCount () : Int = fragments!!.size
    override fun getPageTitle(position: Int): CharSequence? = titles[position].typeName
}
