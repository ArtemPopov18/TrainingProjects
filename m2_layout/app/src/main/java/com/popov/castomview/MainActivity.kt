package com.popov.castomview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.popov.castomview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.my.setTextTop("Верхняя строка")
        binding.my.setTextBottom("Нижняя строка")
    }
}