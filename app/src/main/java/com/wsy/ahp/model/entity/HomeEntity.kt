package com.wsy.ahp.model.entity

data class TypeService(
    val code: Int,
    val message: String,
    val result: TypeRecords,
    val success: Boolean
)

data class TypeRecords (
    val records:List<Type>
    )

data class Type(
    val articleType:Int,
    val typeName:String,
    val id:String,

)

data class RecommendService(
    val code: Int,
    val message: String,
    val result: RecommendRecords,
    val success: Boolean
)

data class RecommendRecords (
    val records:List<Recommend>
)



data class Recommend(
    var createBy: String?,
    var createTime: String?,
    var id: String?,
    var legalType: Int?,
    var legalUnit: String?,
    var recAvater: String?,
    var recCheckpeople: String?,
    var recContent: String?,
    var recIntroduce: String?,
    var recIsClassics: Int,
    var recIsFree: Int,
    var recIsLegal: Int,
    var recNum: Int,
    var recPic: String?,
    var recPublish: String?,
    var recSort: Int,
    var recTag: String?,
    var recTechType: Int,
    var recTitle: String?,
    var recType: Int,
    var recVideo: String?,
    var sysOrgCode: String?,
    var updateBy: String?,
    var updateTime: String?,
    val articleCheckClick: ArticleCheckClick
)

data class ArticleCheckClick(
    var commentCount: Int,
    var favouriteNum: Int,
    var isCollection: Boolean,
    var isFavourite: Boolean,
    var likeNum: Int
)



