package com.popov.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.popov.myapplication.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordsDao = (context?.applicationContext as App).db.wordsDao()
                return MainViewModel(wordsDao) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    is State.Error -> {
                        binding.buttonAdd.isEnabled = false
                        binding.textInputLayout.error = state.message
                    }
                    State.Loading -> {
                        binding.buttonAdd.isEnabled = false
                    }
                    State.Success -> {
                        binding.buttonAdd.isEnabled = true
                        binding.textInputLayout.error = null
                    }
                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.words
                .collect { words ->
                    binding.message.text = words.joinToString(
                        separator = "\r\n"
                    )
                }
        }

        binding.buttonAdd.setOnClickListener {
            viewModel.onAddButton(binding.editText.text.toString())
        }

        binding.buttonClear.setOnClickListener {
            viewModel.buttonClear()
        }

        binding.editText.addTextChangedListener {
            viewModel.stateButtonAdd(binding.editText.text.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}