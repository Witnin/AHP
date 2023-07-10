package com.wsy.ahp.view
// 加载的状态
enum class LoadingStatus {
    InitalLoading, // 初次加载
    Loading, // 正在加载
    Failed, // 加载失败
    Completed // 数据全部加载完
}