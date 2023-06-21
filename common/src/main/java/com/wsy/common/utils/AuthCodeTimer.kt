package com.wsy.common.utils

import android.os.CountDownTimer
import com.wsy.common.ui.view.CountDownListener

class AuthCodeTimer(
    millisInFuture: Long, countDownInterval: Long,
    mCountDownListener: CountDownListener
) :
    CountDownTimer(millisInFuture, countDownInterval) {

    private val mCountDownListener: CountDownListener = mCountDownListener
    override fun onTick(millisUntilFinished: Long) {
        mCountDownListener.isOverTime(false)
        mCountDownListener.countDown(millisUntilFinished)
    }

    override fun onFinish() {
        mCountDownListener.isOverTime(true)
    }
}