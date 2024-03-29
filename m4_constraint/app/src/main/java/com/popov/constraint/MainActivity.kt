package com.popov.constraint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.popov.constraint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textPerformer.text = "The Beatles"
        binding.textCompositionName.text = "Abbey road"
    }
}