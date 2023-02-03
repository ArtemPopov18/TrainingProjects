package com.popov.recyclerview.presation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.popov.recyclerview.R
import com.popov.recyclerview.data.Photo
import com.popov.recyclerview.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    private val mainAdapter = MainAdapter { photo -> onItemClick(photo) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = mainAdapter

        viewModel.mars.onEach {
            mainAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    fun onItemClick(item: Photo){
        val bundle = Bundle()
//        val url = "https://mars.nasa.gov/msl-raw-images/msss/00020/mcam/0020MR0011002000I1_DXXX.jpg"
        val url2 = item.img_src
        bundle.putString("MyArg", url2)
        findNavController().navigate(R.id.action_mainFragment_to_imageMarsFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}