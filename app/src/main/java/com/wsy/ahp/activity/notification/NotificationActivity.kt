package com.wsy.ahp.activity.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.activity.threed.PanoramaActivity
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_notification.sendNotice

@Route(path = ArouterUrl.SYSTEM_NOTIFICATION)
class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        val channel = NotificationChannel("normal", "Normal",NotificationManager.
        IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)
        sendNotice.setOnClickListener {
            val intent = Intent(this, PanoramaActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val notification = NotificationCompat.Builder(this, "normal")
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setSmallIcon(R.drawable.kje)
                .setLargeIcon(
                    BitmapFactory.decodeResource(resources,
                    R.drawable.kje))
                .setContentIntent(pi)
                .build()
            manager.notify(1, notification)
        }

        }
}