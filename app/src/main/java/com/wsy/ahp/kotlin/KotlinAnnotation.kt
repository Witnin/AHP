package com.wsy.ahp.kotlin


fun main(){
    fire(ApiGetArticles())
}

//和一般的声明类似，只是在class前面添加了annotation
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
annotation class ApiDoc(val value: String)

@ApiDoc("修饰类")
class Box {
    @ApiDoc("修饰字段")
    val size = 6
}

@ApiDoc("修饰方法")
fun test() {
}

public enum class Methods {
    GET, POST
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HttpMethod(val method: Methods)

interface Api {
    val name: String
    val version: String
        get() = "1.0"
}

@HttpMethod(Methods.POST)
class ApiGetArticles :Api{
    override val name: String
        get() = "/api.video"
}

fun fire(api:Api){
    val annotations = api.javaClass.annotations
    val method = annotations.find { it is HttpMethod } as? HttpMethod
    println("接口请求方式：${method?.method}")
}