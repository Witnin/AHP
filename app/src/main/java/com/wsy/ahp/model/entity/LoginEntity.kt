package com.wsy.ahp.model.entity



data class LoginService(
    val code: Int,
    val message: String,
    val result: LoginEntity,
    val success: Boolean
)

data class LoginEntity(
    val token:String,
    val userInfo:UserInfo,
    val menu:List<Menu>,
)

data class UserInfo (
    val username:String,
    val realname:String,
)

data class Menu (
    val menuName:String,
    val url:String,
)

data class LoginBody(
    val username: String,
    val password:String
)



