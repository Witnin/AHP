package com.wsy.ahp.activity.recycleviewtest.waterflow

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.activity.article.Article
import com.wsy.ahp.activity.recycleviewtest.RecycleViewArticleAdapter
import com.wsy.ahp.http.common.ArouterUrl
import com.wsy.wsy_library.util.HiStatusBar
import kotlinx.android.synthetic.main.activity_recycle_view_test.recycle_view_test

@Route(path = ArouterUrl.RECYCLE_VIEW_WATER_FLOW)
class RecycleviewWaterFlowActivity : AppCompatActivity() {
    private val articleList = ArrayList<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycleview_water_flow)

        HiStatusBar.setStatusBar(this,true, Color.WHITE,false)

        initArticles() // 初始化水果数据
        var spanCount = 3
        val screenHeight = Resources.getSystem().displayMetrics.heightPixels //屏幕高度
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels//屏幕宽度
        spanCount = if(screenHeight > screenWidth){
            3
        }else{
            6
        }
        val layoutManager = StaggeredGridLayoutManager(spanCount,StaggeredGridLayoutManager.VERTICAL)
        recycle_view_test.layoutManager = layoutManager
        val adapter = RecycleViewWaterFlowAdapter(articleList)
        recycle_view_test.adapter = adapter
    }

    private fun initArticles(){
        repeat(10){
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