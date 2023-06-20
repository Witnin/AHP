package com.wsy.ahp.activity.nowarticle.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * author : within
 * time   : 2023/6/20
 * desc   :  FragmentPagerAdapter  viewpager的适配器
 */
class HomeAdapter (fm:FragmentManager,fragments:ArrayList<Fragment>):FragmentPagerAdapter(fm){
    private val mFragments:ArrayList<Fragment> = fragments
    override fun getItem(position: Int): Fragment = mFragments[position]
    override fun getCount(): Int =mFragments.size
}