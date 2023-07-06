package com.wsy.ahp.fragment.nowarticle.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wsy.ahp.fragment.nowarticle.VideoListFragment

class VideoAdapter (fragment: Fragment, private val items: Array<String>):
FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int =items.size

    override fun createFragment(position: Int): Fragment {
        return VideoListFragment.getInstance(items[position])
    }
}