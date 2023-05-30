package com.wsy.ahp.activity.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl.LISTVIEW_ARTICLE
import kotlinx.android.synthetic.main.activity_list_view.listview

@Route(path = LISTVIEW_ARTICLE)
class ListViewActivity : AppCompatActivity() {

    private val articleList = ArrayList<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        initArticles()
        val adapter = ArticleAdapter(this,R.layout.article_item,articleList)
        listview.adapter = adapter
        listview.setOnItemClickListener { _, _, position, _ ->
            val article = articleList[position]
            Toast.makeText(this, article.name, Toast.LENGTH_SHORT).show()
        }
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