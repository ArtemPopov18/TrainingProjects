package com.example.skillbox_hw_quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skillbox_hw_quiz.databinding.ActivityWelcomePictureBinding

class ActivityWelcomePicture : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomePictureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomePictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomProceed.setOnClickListener {

        }
    }
}