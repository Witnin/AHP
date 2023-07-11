package com.wsy.ahp.fragment.nowarticle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.wsy.ahp.R
import kotlinx.android.synthetic.main.fragment_article_home.main_recyclerview


class ArticleHomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_home, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RecyclerView.RecycledViewPool().also {
            it.setMaxRecycledViews(1, 6)
            it.setMaxRecycledViews(2, 6)
            it.setMaxRecycledViews(3, 6)
            it.setMaxRecycledViews(4, 6)
            it.setMaxRecycledViews(5, 6)
            it.setMaxRecycledViews(6, 6)
            it.setMaxRecycledViews(7, 6)
            it.setMaxRecycledViews(8, 6)
            it.setMaxRecycledViews(9, 6)
            main_recyclerview.setRecycledViewPool(it)
        }

        val layoutManager = VirtualLayoutManager(requireActivity()).also {
            main_recyclerview.layoutManager = it
        }

        main_recyclerview.adapter = DelegateAdapter(layoutManager, false)

    }



}