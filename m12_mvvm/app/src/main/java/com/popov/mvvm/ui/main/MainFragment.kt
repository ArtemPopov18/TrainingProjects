package com.popov.mvvm.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.popov.mvvm.databinding.FragmentMainBinding

private const val TAG = "MainActivity"

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
//        val view = binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textQuery.addTextChangedListener {
            if (it != null) {
                viewModel.stateButton(it?.length!! >= 3)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.stateIsEnabled.collect {
                binding.bottomSearch.isEnabled = it
            }
        }

        binding.bottomSearch.setOnClickListener {
            val textEdit = binding.textQuery.text.toString()
            viewModel.onButtonClick(textEdit)
        }

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.state
                    .collect { state ->
                        when (state) {
                            State.Loading -> {
                                binding.progressBar.isVisible = true
                            }
                            is State.Error -> {
                                binding.message.text = state.editTextErrorMy
                                binding.progressBar.isVisible = false
                            }
                            State.ReadyToWork -> {
                                binding.message.text = "Здесь будет отображаться результат запроса"
                            }
                            is State.Success -> {
                                binding.progressBar.isVisible = false
                                binding.message.text = state.editTextQuery
                            }
                        }
                    }
            }

        viewLifecycleOwner.lifecycleScope
            .launchWhenStarted {
                viewModel.stateChannel
                    .collect {
                        Log.d(TAG, "равно ${binding.textQuery.text}")
                    }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}