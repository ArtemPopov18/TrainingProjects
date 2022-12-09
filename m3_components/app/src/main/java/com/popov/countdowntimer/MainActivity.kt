package com.popov.countdowntimer

import android.os.Bundle
import android.os.CountDownTimer
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

        binding.bottomStart.setOnClickListener {
            if (binding.bottomStart.text == "Start") {
                startCountDownTimer(second * secondToMilliseconds)
                binding.bottomStart.text = "Stop"
                binding.slider.isEnabled = false
            } else {
                binding.bottomStart.text = "Start"
                binding.slider.isEnabled = true
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

    private fun startCountDownTimer(sliderValue: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(sliderValue, secondToMilliseconds.toLong()) {
            override fun onTick(p0: Long) {
                binding.scoreboardTime.text = (p0 / secondToMilliseconds).toString()
                binding.progressBarTime.progress = (p0 / secondToMilliseconds).toInt()
                second = (p0 / secondToMilliseconds)
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Stop timer", Toast.LENGTH_SHORT).show()
                binding.bottomStart.text = "Start"
                binding.slider.isEnabled = true
            }
        }.start()
    }
}
