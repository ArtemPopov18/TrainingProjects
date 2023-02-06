package com.popov.permissions.presation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.popov.permissions.App
import com.popov.permissions.R
import com.popov.permissions.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collect

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }
    private val mainAdapter = MainAdapter()
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val photoDao = (context?.applicationContext as App).db.photoDao()
                return MainViewModel(photoDao) as T
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
        binding.cameraButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_mainFragment_to_photographFragment
            )
        }
        binding.deleteButton.setOnClickListener {
            viewModel.delete()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.photo.collect { photo ->
                Log.d("AAA", "$photo")
                mainAdapter.setData(photo)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}