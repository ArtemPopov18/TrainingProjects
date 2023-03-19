package com.popov.myrickandmorty.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val pagedAdapter = CharacterPagedListAdapter { character -> onItemClick(character) }


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

        pagedAdapter.addLoadStateListener {
            val refreshState = it.refresh
            if (refreshState is LoadState.Error) {
                binding.recycler.isVisible = false
                binding.reloadButton.isVisible = true
                Toast.makeText(
                    requireContext(),
                    refreshState.error.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                binding.recycler.isVisible = true
                binding.reloadButton.isVisible = false
            }
        }
        binding.reloadButton.setOnClickListener {
            reloadList()
        }
    }

    private fun reloadList() {
        binding.recycler.adapter = pagedAdapter
        viewModel.pagedCharacter.onEach {
            pagedAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        binding.recycler.adapter = pagedAdapter
    }

    fun onItemClick(item: com.popov.myrickandmorty.data.Character) {
//        val bundle = Bundle()
        val action = MainFragmentDirections.actionMainFragmentToCharacterFragment(item)
//        bundle.putParcelable("MyArg", item)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}