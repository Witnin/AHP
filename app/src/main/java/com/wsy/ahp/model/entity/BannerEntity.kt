package com.wsy.ahp.model.entity

data class CommonService(
    val code: Int,
    val message: String,
    val result: Records,
    val success: Boolean
)

data class Records (
    val records:List<Banner>
    )

data class Banner(
    val bannerRemarks:String,
    val bannerUrl:String,
    val id:String,
    val bannerDes:String,
    val recId:String,

)

