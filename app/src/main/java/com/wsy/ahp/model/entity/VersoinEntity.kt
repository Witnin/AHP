package com.wsy.ahp.model.entity

data class VersionService(
    val code: Int,
    val message: String,
    val result: Version,
    val success: Boolean
)


data class Version(
    val sysValue:String,
    val sysVersion:String,
    val sysDes:String,
    val id:String,
    val sysRemarks:String,

)

