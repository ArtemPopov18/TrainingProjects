package com.example.bicycle.ui.main

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.bicycle.App
import com.example.bicycle.R
import com.example.bicycle.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import org.koin.android.ext.android.get

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val daggerViewModel: MainViewModel by viewModels {viewModelFactory}

    private val koinViewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(bicycleFactory = get()) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.component.injectMainFragment(this)

        binding.daggerButton.setOnClickListener {
            val bicycle = daggerViewModel.bicycleFactory.build("schoolboy", Color.parseColor("#7FFF0000"))

            Toast.makeText(requireContext(), bicycle.toString(), Toast.LENGTH_LONG).show()
//            Snackbar.make(binding.root, bicycle.toString(), Toast.LENGTH_LONG).show()
        }

        binding.koinButton.setOnClickListener {
            val bicycle = koinViewModel.bicycleFactory.build("kama", Color.parseColor("#000000"))
            Toast.makeText(requireContext(), bicycle.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}