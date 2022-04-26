package com.nirwashh.android.playingaudio

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import com.google.android.material.slider.Slider
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
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolue = (audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) / 15 * 100
        //переводим в проценты для удобочитаемости, так как макс значение громкости 15

        b.slider.valueTo = maxVolue.toFloat()
        b.btnPlay.setOnClickListener { start() }
        b.btnPause.setOnClickListener { pause() }
        b.btnStop.setOnClickListener { stop() }
        b.btnBack.setOnClickListener { back() }
        b.btnForward.setOnClickListener { forward() }
        b.slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {

            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: Slider) {
            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: Slider) {
            }
        })
        b.slider.addOnChangeListener { slider, value, fromUser ->
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value.toInt(), 0)
        }


    }


    private fun start() {
        mediaPlayer.start()
        if (b.btnPlay.isPressed) {
            b.btnPlay.animate().scaleX(1.5F).scaleY(1.5F).duration = 500
            b.btnPause.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnStop.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnBack.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnForward.animate().scaleX(1F).scaleY(1F).duration = 500
        }
    }

    private fun pause() {
        mediaPlayer.pause()
        if (b.btnPause.isPressed) {
            b.btnPlay.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnPause.animate().scaleX(1.5F).scaleY(1.5F).duration = 500
            b.btnStop.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnBack.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnForward.animate().scaleX(1F).scaleY(1F).duration = 500
        }
    }

    private fun stop() {
        mediaPlayer.pause()
        if (b.btnStop.isPressed) {
            b.btnPlay.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnPause.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnStop.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnBack.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnForward.animate().scaleX(1F).scaleY(1F).duration = 500
        }
    }

    private fun back() {
        mediaPlayer.pause()
        if (b.btnBack.isPressed) {
            b.btnPlay.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnPause.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnStop.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnBack.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnForward.animate().scaleX(1F).scaleY(1F).duration = 500
        }
    }

    private fun forward() {
        mediaPlayer.pause()
        if (b.btnForward.isPressed) {
            b.btnPlay.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnPause.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnStop.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnBack.animate().scaleX(1F).scaleY(1F).duration = 500
            b.btnForward.animate().scaleX(1F).scaleY(1F).duration = 500
        }
    }

}