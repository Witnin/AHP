package com.wsy.wsy_library.util

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import java.text.SimpleDateFormat
import java.util.Date

/**
 * 键盘回弹
 */
fun getGlobalListener(
        decorView: View,
        contentView: View
    ): ViewTreeObserver.OnGlobalLayoutListener {
        return ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            decorView.getWindowVisibleDisplayFrame(r)
            val height = decorView.context.resources.displayMetrics.heightPixels
            val diff = height - r.bottom
            if (diff != 0) {
                if (contentView.paddingBottom != diff) {
                    contentView.setPadding(0, 0, 0, diff)
                }
            }else {
                    if (contentView.paddingBottom != 0) {
                        contentView.setPadding(0, 0, 0, 0)
                    }

                }
        }
    }

 fun getTime(date: Date): String? { //可根据需要自行截取数据显示
    Log.d("getTime()", "choice date millis: " + date.getTime())
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return format.format(date)
}




