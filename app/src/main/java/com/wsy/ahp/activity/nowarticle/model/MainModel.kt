package com.wsy.ahp.activity.nowarticle.model

/**
 * author : within
 * time   : 2023/6/20
 * desc   :
 */
data class MainModel(
    val data: List<Data>,
    val stat: String
)

data class Data(
    val author_name: String,
    val category: String,
    val date: String,
    val thumbnail_pic_s: String,
    val thumbnail_pic_s02: String,
    val thumbnail_pic_s03: String,
    val title: String,
    val uniquekey: String,
    val url: String
)