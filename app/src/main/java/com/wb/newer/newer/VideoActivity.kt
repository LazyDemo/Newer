package com.wb.newer.newer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wb.newer.newer.widget.media.AndroidMediaController
import kotlinx.android.synthetic.main.activity_video.*
import tv.danmaku.ijk.media.player.IjkMediaPlayer

class VideoActivity : AppCompatActivity() {

    lateinit var androidMediaController:AndroidMediaController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        androidMediaController = AndroidMediaController(this,false)

        // init player
        IjkMediaPlayer.loadLibrariesOnce(null)
        IjkMediaPlayer.native_profileBegin("libijkplayer.so")


        videoView.setMediaController(androidMediaController)
        videoView.setVideoPath("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8")
        videoView.start()
    }

    override fun onStart() {
        super.onStart()
    }

    private var mBackPressed: Boolean = false

    override fun onBackPressed() {
        super.onBackPressed()
        mBackPressed = true

    }

    override fun onStop() {
        super.onStop()
        if (mBackPressed || !videoView.isBackgroundPlayEnabled) {
            videoView.stopPlayback()
            videoView.release(true)
            videoView.stopBackgroundPlay()
        } else {
            videoView.enterBackground()
        }
        IjkMediaPlayer.native_profileEnd()
    }
}
