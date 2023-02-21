package com.popov.myrickandmorty.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.popov.myrickandmorty.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val pagedAdapter = CharacterPagedListAdapter()


    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reloadList()
//        binding.recycler.adapter = pagedAdapter
//
//        viewModel.pagedCharacter.onEach {
//            pagedAdapter.submitData(it)
//        }.launchIn(viewLifecycleOwner.lifecycleScope)

        pagedAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Error) {
                binding.recycler.isVisible = false
                binding.reloadButton.isVisible = true
            }
        }
        binding.reloadButton.setOnClickListener {
            reloadList()
        }

//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            viewModel.pagedCharacter.collect{
//                when(it){
//                    is Error -> {
//                        binding.recycler.isVisible = false
//                        binding.reloadButton.isVisible = true
//                    }
//                    else -> {
//                        pagedAdapter.submitData(it)
//                    }
//                }
//            }
//        }
    }
    private fun reloadList() {
        binding.recycler.adapter = pagedAdapter
        viewModel.pagedCharacter.onEach {
            pagedAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        binding.recycler.adapter = pagedAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}