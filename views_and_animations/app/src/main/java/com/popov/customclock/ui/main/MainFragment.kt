package com.popov.customclock.ui.main

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.popov.customclock.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var pauseOffset: Long = 0


    companion object {
        fun newInstance() = MainFragment()
    }

//    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonStart.setOnClickListener {
            binding.chronometerMy.base = SystemClock.elapsedRealtime() - pauseOffset
            binding.chronometerMy.start()
            binding.myClock.start()
        }
        binding.buttonStop.setOnClickListener {
            binding.chronometerMy.stop()
            pauseOffset = SystemClock.elapsedRealtime() - binding.chronometerMy.base
            binding.myClock.stop()
        }
        binding.buttonReset.setOnClickListener {
            binding.chronometerMy.base = SystemClock.elapsedRealtime()
            pauseOffset = 0
            binding.myClock.reset()
        }
        binding.chronometerMy.setOnChronometerTickListener {
            if (((binding.chronometerMy.drawingTime - binding.chronometerMy.base) / 1000) > 0) {
                val times =
                    ((binding.chronometerMy.drawingTime - binding.chronometerMy.base) / 1000).toInt()
                binding.myClock.updateTime(times)
            } else {
                binding.myClock.updateTime(0)
            }
        }
        bindToTimeChanges(binding.myClock)
    }

    fun bindToTimeChanges(clock: ICustomAnalogClock) {
        clock.addUpdateListener {
            Log.d("AAA", it.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}