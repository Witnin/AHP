package com.wsy.ahp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.wsy.ahp.R
import com.wsy.ahp.activity.thread.ServiceTestActivity
import kotlin.concurrent.thread

class MyService : Service() {

    private val mBinder = DownloadBinder()
    class DownloadBinder : Binder() {
        fun startDownload() {
            Log.d("MyService", "startDownload executed")
        }
        fun getProgress(): Int {
            Log.d("MyService", "getProgress executed")
            return 0
        }
    }


    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    /**
     * 在Service 创建的时候调用
     */
    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "onCreate executed")

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        val channel = NotificationChannel(
            "my_service", "前台Service通知",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        manager.createNotificationChannel(channel)
        val intent = Intent(this, ServiceTestActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("This is content title")
            .setContentText("This is content text")
            .setSmallIcon(R.drawable.kje)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.kje))
            .setContentIntent(pi)
            .build()
        startForeground(1, notification)
    }

    /**
     * 在Service 启动的时候调用
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("MyService", "onStartCommand executed")
        thread {
            // 处理具体的逻辑
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 在Service  销毁的时候调用
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "onDestroy executed")
    }
}