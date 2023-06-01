package com.wsy.ahp.activity.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory

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
        //系统栏通知
        val channel = NotificationChannel("normal", "Normal",NotificationManager.
        IMPORTANCE_DEFAULT)

        //横幅通知
        val channel2 = NotificationChannel("important", "Important",
            NotificationManager.IMPORTANCE_HIGH)
        manager.createNotificationChannel(channel2)

        manager.createNotificationChannel(channel)
        sendNotice.setOnClickListener {
            val intent = Intent(this, PanoramaActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            val notification = NotificationCompat.Builder(this, "important")
                .setContentTitle("This is content title")
//                .setContentText("Learn how to build notifications, send and sync data,\n" +
//                        " and use voice actions.Get the official Android IDE and developer tools to\n" +
//                        " build apps for Android。")
                /***
                 * 长文本
                 */
//                .setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to build notifications," +
//                        "send and sync data, and use voice actions. Get the official " +
//                        "Android IDE and developer tools to build apps for Android."))
                /**
                 * 图片
                 */
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(
                    BitmapFactory.decodeResource(resources, R.drawable.kje)))

                .setSmallIcon(R.drawable.kje)
                .setLargeIcon(
                    BitmapFactory.decodeResource(resources,
                    R.drawable.kje))
                .setContentIntent(pi)
                .setAutoCancel(true) //点击后通知消失
                .build()
            manager.notify(1, notification)
        }

        }
}