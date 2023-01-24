package com.popov.mvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.popov.mvvm.databinding.FragmentMainBinding

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
                                binding.bottomSearch.isEnabled = false
                            }
                            State.Success -> {
                                binding.progressBar.isVisible = false
                                binding.bottomSearch.isEnabled = true
                            }
                            is State.Error -> {
                                binding.message.text = state.editTextErrorMy
                                binding.progressBar.isVisible = false
                                binding.bottomSearch.isEnabled = false
                            }
                        }
                    }
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}