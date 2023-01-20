package com.popov.countdowntimer

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.popov.countdowntimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null
    private val secondToMilliseconds = 1000
    private var second: Long = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                clickSlider(slider)
            }

            override fun onStopTrackingTouch(slider: Slider) {
                clickSlider(slider)
            }
        })

        if (savedInstanceState != null) {
            binding.bottomStart.text = savedInstanceState.getString(KEY_TEXT_BUTTON)
            binding.progressBarTime.max = savedInstanceState.getInt(KEY_BAR_TIME_MAX)
            if (binding.bottomStart.text == "Stop") {
                binding.slider.isEnabled = savedInstanceState.getBoolean(KEY_SLIDER_ISENABLED)
                second = savedInstanceState.getLong(KEY_SECOND)
                startCountDownTimer(second * secondToMilliseconds)
            } else {
                binding.scoreboardTime.text = savedInstanceState.getString(KEY_TEXT_TIMER)
                binding.progressBarTime.progress = savedInstanceState.getInt(KEY_BAR_TIME_PROGRESS)
            }
        }

        binding.bottomStart.setOnClickListener {
            if (binding.bottomStart.text == "Start") {
                startCountDownTimer(second * secondToMilliseconds)
                binding.bottomStart.text = "Stop"
                binding.slider.isEnabled = false
            } else {
                timer?.onFinish()
                timer?.cancel()
            }
        }
    }

    private fun clickSlider(slider: Slider) {
        binding.scoreboardTime.text = (slider.value.toInt()).toString()
        binding.progressBarTime.max = slider.value.toInt()
        binding.progressBarTime.progress = slider.value.toInt()
        second = binding.slider.value.toLong()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_TEXT_BUTTON, binding.bottomStart.text.toString())
        outState.putBoolean(KEY_SLIDER_ISENABLED, binding.slider.isEnabled)
        outState.putLong(KEY_SECOND, second)
        outState.putString(KEY_TEXT_TIMER, binding.scoreboardTime.text.toString())
        outState.putInt(KEY_BAR_TIME_MAX, binding.progressBarTime.max)
        outState.putInt(KEY_BAR_TIME_PROGRESS, binding.progressBarTime.progress)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    private fun startCountDownTimer(sliderValue: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(sliderValue, secondToMilliseconds.toLong()) {
            override fun onTick(p0: Long) {
                binding.scoreboardTime.text = (p0 / secondToMilliseconds).toString()
                binding.progressBarTime.progress = (p0 / secondToMilliseconds).toInt()
                second = (p0 / secondToMilliseconds)
                Log.d(TAG, "Поток идет ${(p0 / secondToMilliseconds)}")
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Stop timer", Toast.LENGTH_SHORT).show()
                binding.bottomStart.text = "Start"
                binding.slider.isEnabled = true
            }
        }.start()
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val KEY_TEXT_BUTTON = "key_text_button"
        private const val KEY_SLIDER_ISENABLED = "key_slider_isEnabled"
        private const val KEY_SECOND = "key_SECOND"
        private const val KEY_TEXT_TIMER = "key_text_timer"
        private const val KEY_BAR_TIME_MAX = "key_bar_time_max"
        private const val KEY_BAR_TIME_PROGRESS = "key_bar_time_progress"
    }
}
