package com.nirwashh.android.playingaudio

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import com.nirwashh.android.playingaudio.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.stuff)
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val volume = 0
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)

        b.btnPlay.setOnClickListener { start() }
        b.btnPause.setOnClickListener { pause() }
        b.btnBack.setOnClickListener { back() }
        b.btnForward.setOnClickListener { forward() }

        b.slider.valueTo = maxVolume.toFloat()
        b.slider.addOnChangeListener { slider, value, fromUser ->
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value.toInt(), 0)
        }

        seekBar()


    }

    private fun seekBar() {
        b.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        runnable = Runnable {
            b.seekbar.progress = mediaPlayer.currentSeconds

            val currentPositionText = createTimer(mediaPlayer.currentSeconds)
            b.tvCurrentPosition.text = currentPositionText
            val endPositionText = createTimer(mediaPlayer.seconds - mediaPlayer.currentSeconds)
            b.tvEndPosition.text = endPositionText

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

    }

    private fun createTimer(duration: Int): String {
        val minutes = duration / 60
        val seconds = duration - (minutes * 60)
        val minuteString = if (minutes < 10) "0$minutes" else "$minutes"
        val secondString = if (seconds < 10) "0$seconds" else "$seconds"
        return "$minuteString:$secondString"
    }

    private val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }

    private val MediaPlayer.currentSeconds: Int
        get() {
            return this.currentPosition/1000
        }

    private fun start() {
        mediaPlayer.start()
        if (mediaPlayer.isPlaying) {
            b.btnPlay.animate().scaleX(0.5F).scaleY(0.5F).duration = 500
            b.btnPlay.visibility = View.GONE
            b.btnPause.visibility = View.VISIBLE
            b.btnPause.animate().scaleX(1.5F).scaleY(1.5F).duration = 500
            b.btnBack.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnForward.animate().scaleX(1F).scaleY(1F).duration = 500
        }
    }

    private fun pause() {
        mediaPlayer.pause()
        if (!mediaPlayer.isPlaying) {
            b.btnPause.animate().scaleX(0.5F).scaleY(0.5F).duration = 500
            b.btnPause.visibility = View.GONE
            b.btnPlay.visibility = View.VISIBLE
            b.btnPlay.animate().scaleX(1.5F).scaleY(1.5F).duration = 500
            b.btnBack.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnForward.animate().scaleX(1F).scaleY(1F).duration = 500
        }
    }


    private fun back() {
        mediaPlayer.pause()
        if (b.btnBack.isPressed) {
            b.btnPlay.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnPause.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnBack.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnForward.animate().scaleX(1F).scaleY(1F).duration = 500
        }
    }

    private fun forward() {
        mediaPlayer.pause()
        if (b.btnForward.isPressed) {
            b.btnPlay.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnPause.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnBack.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnForward.animate().scaleX(1F).scaleY(1F).duration = 500
        }
    }

}