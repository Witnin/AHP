##Android框架项目

语言：Java+Kotlin
路由导航：ARouter
多线程操作：Executor
---------------------------
模块：
-library
--log
--executor
--hiRestful
-common
-ui
-threed
--3D全景
----------------------------

Kotlin协程的写法
* GlobalScope.launch(Dispatchers.Main){
* val value1 = request1()
* val value2 = request2(value1)
* val value3 = request3(value2)
* updateUI(value3)
* }
* suspend request1()
* suspend request2(..)
* suspend request3(..)