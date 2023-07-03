package com.wsy.wsy_library.util

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver



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




