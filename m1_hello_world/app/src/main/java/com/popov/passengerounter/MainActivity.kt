package com.popov.passengerounter

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.popov.passengerounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.numberPassengers.text = Counter.count.toString()
        binding.scoreboard.text = "Все места свободны"

        binding.plusOne.setOnClickListener {
            Counter.count++
            binding.numberPassengers.text = Counter.count.toString()
            if (Counter.count == 50) {
                binding.scoreboard.setTextColor(Color.RED)
                binding.plusOne.visibility = View.INVISIBLE
                binding.scoreboard.text = "Пассажиров слишком много!"
                binding.restart.visibility = View.VISIBLE
            }
            if (Counter.count > 0 && Counter.count != 50) {
                binding.scoreboard.setTextColor(Color.BLUE)
                binding.minusOne.visibility = View.VISIBLE
                binding.scoreboard.text = "Осталось мест ${50 - Counter.count}"
            }
        }

        binding.minusOne.setOnClickListener {
            Counter.count--
            binding.numberPassengers.text = Counter.count.toString()
            if (Counter.count < 50 && Counter.count != 0) {
                binding.scoreboard.setTextColor(Color.BLUE)
                binding.plusOne.visibility = View.VISIBLE
                binding.scoreboard.text = "Осталось мест ${50 - Counter.count}"
            }
            if (Counter.count == 0) {
                binding.scoreboard.setTextColor(Color.GREEN)
                binding.minusOne.visibility = View.INVISIBLE
                binding.scoreboard.text = "Все места свободны"
            }
        }

        binding.restart.setOnClickListener {
            val intent = intent
            finish()
            startActivity(intent)
        }
    }
}