package com.wsy.ahp.activity.media

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.wsy.ahp.R
import com.wsy.ahp.http.common.ArouterUrl
import kotlinx.android.synthetic.main.activity_play_video.*
import kotlinx.android.synthetic.main.activity_play_video.videoView

@Route(path = ArouterUrl.SYSTEM_VIDEO)
class PlayVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView.setVideoURI(uri)
        play.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start() // 开始播放
            }
        }
        pause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause() // 暂停播放
            }
        }
        replay.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.resume() // 重新播放
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }

}