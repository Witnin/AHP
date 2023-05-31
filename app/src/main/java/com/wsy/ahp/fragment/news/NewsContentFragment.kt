package com.wsy.ahp.fragment.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wsy.ahp.R
import kotlinx.android.synthetic.main.fragment_news_content.contentLayout
import kotlinx.android.synthetic.main.fragment_news_content.newsContent
import kotlinx.android.synthetic.main.fragment_news_content.newsTitle


class NewsContentFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_content, container, false)
    }

    fun refresh(title: String, content: String) {
        contentLayout.visibility = View.VISIBLE
        newsTitle.text = title // 刷新新闻的标题
        newsContent.text = content // 刷新新闻的内容
    }
}