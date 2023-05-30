package com.wsy.ahp.activity.recycleviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.activity.article.Article
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_recycle_view_test.recycle_view_test

@Route(path = ArouterUrl.RECYCLE_VIEW_ARTICLE)
class RecycleViewTestActivity : AppCompatActivity() {
    private val articleList = ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view_test)
        initArticles() // 初始化水果数据
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycle_view_test.layoutManager = layoutManager
        val adapter = RecycleViewArticleAdapter(articleList)
        recycle_view_test.adapter = adapter

    }

    private fun initArticles(){
        repeat(2){
            articleList.add(Article("Apple", R.drawable.guan))
            articleList.add(Article("Banana", R.drawable.test))
            articleList.add(Article("Orange", R.drawable.answer))
            articleList.add(Article("Watermelon", R.drawable.icon_case))
            articleList.add(Article("Apple", R.drawable.guan))
            articleList.add(Article("Banana", R.drawable.test))
            articleList.add(Article("Orange", R.drawable.answer))
            articleList.add(Article("Watermelon", R.drawable.icon_case))
            articleList.add(Article("Apple", R.drawable.guan))
            articleList.add(Article("Banana", R.drawable.test))
            articleList.add(Article("Orange", R.drawable.answer))
            articleList.add(Article("Watermelon", R.drawable.icon_case))
        }
    }
}