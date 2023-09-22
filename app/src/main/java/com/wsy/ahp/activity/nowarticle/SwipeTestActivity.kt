package com.wsy.ahp.activity.nowarticle

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.Gravity
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lzf.easyfloat.EasyFloat
import com.lzf.easyfloat.enums.ShowPattern
import com.lzf.easyfloat.enums.SidePattern
import com.lzf.easyfloat.interfaces.OnTouchRangeListener
import com.lzf.easyfloat.permission.PermissionUtils
import com.lzf.easyfloat.utils.DragUtils
import com.lzf.easyfloat.widget.BaseSwitchView
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_on_click.onClick

@Route(path = ArouterUrl.flb)
class SwipeTestActivity : AppCompatActivity(),BGASwipeBackHelper.Delegate {

    companion object {
        const val FLOAT_TAG = "SwipeTestActivity"
    }

    private var noPermission = false

    var slideOffset = 0f
    private lateinit var vibrator: Vibrator
    lateinit var bgaSwipeBackHelper: BGASwipeBackHelper
    private var vibrating = false
    private var contractTag = "contractFloat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_test)
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        bgaSwipeBackHelper = BGASwipeBackHelper(this, this)
    }

    override fun isSupportSwipeBack(): Boolean {
        return true
    }

    override fun onSwipeBackLayoutSlide(slideOffset: Float) {
        this.slideOffset = slideOffset
    }

    override fun onSwipeBackLayoutCancel() {

    }

    override fun onSwipeBackLayoutExecuted() {
        if (!noPermission) bgaSwipeBackHelper.swipeBackward()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        DragUtils.registerSwipeAdd(ev, object : OnTouchRangeListener {
            override fun touchInRange(inRange: Boolean, view: BaseSwitchView) {
                setVibrator(inRange)
                view.findViewById<ImageView>(com.lzf.easyfloat.R.id.iv_add)
                    .setImageResource(
                        if (inRange) com.lzf.easyfloat.R.drawable.add_selected else com.lzf.easyfloat.R.drawable.add_normal
                    )
            }

            override fun touchUpInRange() {
                noPermission = !PermissionUtils.checkPermission(this@SwipeTestActivity)
                showFloat()
            }
        }, slideOffset = slideOffset)
        return super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (bgaSwipeBackHelper.isSliding) return
        bgaSwipeBackHelper.backward()
    }


    @SuppressLint("MissingPermission")
    fun setVibrator(inRange: Boolean) {
        if (!vibrator.hasVibrator() || (inRange && vibrating)) return
        vibrating = inRange
        if (inRange) if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else vibrator.vibrate(100)
        else vibrator.cancel()
    }

    /**
     * 注册拖拽关闭
     */
    fun registerDragClose(event: MotionEvent) =
        DragUtils.registerDragClose(event, object : OnTouchRangeListener {
            override fun touchInRange(inRange: Boolean, view: BaseSwitchView) {
                setVibrator(inRange)
                view.findViewById<TextView>(com.lzf.easyfloat.R.id.tv_delete).text =
                    if (inRange) "松手删除" else "删除浮窗"

                view.findViewById<ImageView>(com.lzf.easyfloat.R.id.iv_delete)
                    .setImageResource(
                        if (inRange) com.lzf.easyfloat.R.drawable.icon_delete_selected
                        else com.lzf.easyfloat.R.drawable.icon_delete_normal
                    )
            }

            override fun touchUpInRange() {
                EasyFloat.dismiss(SwipeTestActivity.FLOAT_TAG, true)
            }
        }, showPattern = ShowPattern.ALL_TIME)


    /**
     * 显示控制中心（假装）
     */
    fun showContractFloat() = EasyFloat.with(application)
        .setTag(contractTag)
        .setLayout(R.layout.float_contract) {
            it.findViewById<TextView>(R.id.tv_back).setOnClickListener {
                EasyFloat.dismiss(contractTag)
            }
        }
        .setShowPattern(ShowPattern.FOREGROUND)
        .setImmersionStatusBar(true)
        .setMatchParent(widthMatch = true, heightMatch = true)
        .setDragEnable(false)
        .setAnimator(null)
        .show()

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (EasyFloat.isShow(contractTag)) {
                EasyFloat.dismiss(contractTag)
                return true
            }
        }
        return super.onKeyDown(keyCode, event)

//        return keyCode == KeyEvent.KEYCODE_BACK


    }

    private fun showFloat() = EasyFloat.with(this.applicationContext)
        .setTag(FLOAT_TAG)
        .setShowPattern(ShowPattern.FOREGROUND)
        .setImmersionStatusBar(true)
        .setGravity(Gravity.END, 0, 500)
        .setSidePattern(SidePattern.RESULT_HORIZONTAL)
//        .setFilter(SecondActivity::class.java)
        .setLayout(R.layout.float_swipe) {
            it.findViewById<ConstraintLayout>(R.id.cl_content).setOnClickListener {
                ARouter.getInstance().build(ArouterUrl.threeDimen).navigation()
            }
        }
        .registerCallback {
            createResult { _, _, _ ->
                if (noPermission && !this@SwipeTestActivity.isFinishing) bgaSwipeBackHelper.swipeBackward()
            }

            drag { view, event ->
                // 注册拖拽关闭
                registerDragClose(event)

                view.findViewById<ConstraintLayout>(R.id.cl_content)
                    .setBackgroundResource(R.drawable.corners_blue)
            }

            dragEnd {
                it.findViewById<ConstraintLayout>(R.id.cl_content).apply {
                    val location = IntArray(2)
                    getLocationOnScreen(location)
                    setBackgroundResource(if (location[0] > 10) R.drawable.corners_left else R.drawable.corners_right)
                }
            }

        }
        .show()
}