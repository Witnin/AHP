package com.wsy.ahp.activity.recycleviewtest.waterflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.activity.article.Article
import com.wsy.ahp.activity.recycleviewtest.RecycleViewArticleAdapter
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_recycle_view_test.recycle_view_test

@Route(path = ArouterUrl.RECYCLE_VIEW_WATER_FLOW)
class RecycleviewWaterFlowActivity : AppCompatActivity() {
    private val articleList = ArrayList<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycleview_water_flow)
        initArticles() // 初始化水果数据
        val layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        recycle_view_test.layoutManager = layoutManager
        val adapter = RecycleViewWaterFlowAdapter(articleList)
        recycle_view_test.adapter = adapter
    }

    private fun initArticles(){
        repeat(2){
            articleList.add(Article(getRandomLengthString("Apple"), R.drawable.guan))
            articleList.add(Article(getRandomLengthString("Banana"), R.drawable.test))
            articleList.add(Article(getRandomLengthString("Orange"), R.drawable.answer))
            articleList.add(Article(getRandomLengthString("Watermelon"), R.drawable.icon_case))
            articleList.add(Article(getRandomLengthString("Apple"), R.drawable.guan))
            articleList.add(Article(getRandomLengthString("Banana"), R.drawable.test))
            articleList.add(Article(getRandomLengthString("Orange"), R.drawable.answer))
            articleList.add(Article(getRandomLengthString("Watermelon"), R.drawable.icon_case))
            articleList.add(Article(getRandomLengthString("Apple"), R.drawable.guan))
            articleList.add(Article(getRandomLengthString("Banana"), R.drawable.test))
            articleList.add(Article(getRandomLengthString("Orange"), R.drawable.answer))
            articleList.add(Article(getRandomLengthString("Watermelon"), R.drawable.icon_case))
            articleList.add(Article(getRandomLengthString("Apple"), R.drawable.guan))
            articleList.add(Article(getRandomLengthString("Banana"), R.drawable.test))
            articleList.add(Article(getRandomLengthString("Orange"), R.drawable.answer))
            articleList.add(Article(getRandomLengthString("Watermelon"), R.drawable.icon_case))
            articleList.add(Article(getRandomLengthString("Apple"), R.drawable.guan))
            articleList.add(Article(getRandomLengthString("Banana"), R.drawable.test))
            articleList.add(Article(getRandomLengthString("Orange"), R.drawable.answer))
            articleList.add(Article(getRandomLengthString("Watermelon"), R.drawable.icon_case))
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }

}