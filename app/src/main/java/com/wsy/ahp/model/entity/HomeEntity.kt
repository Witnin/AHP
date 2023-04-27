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

