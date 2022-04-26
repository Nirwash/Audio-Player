package com.nirwashh.android.playingaudio

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nirwashh.android.playingaudio.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var b: ActivityMainBinding
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.stuff)
        b.btnPlay.setOnClickListener { play() }
        b.btnPause.setOnClickListener { pause() }
        if (mediaPlayer. > 0) {
            val currentPosition = (mediaPlayer.duration / mediaPlayer.currentPosition) * 100
            b.progressBar.apply {
                visibility = View.VISIBLE
                progress = currentPosition
            }

        }

    }

    private fun play() {
        mediaPlayer.start()
        b.btnPlay.visibility = View.GONE
        b.btnPause.visibility = View.VISIBLE
    }

    private fun pause() {
        mediaPlayer.pause()
        b.btnPlay.visibility = View.VISIBLE
        b.btnPause.visibility = View.GONE
    }

}