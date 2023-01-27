package com.popov.retrofit.ui.main


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.popov.retrofit.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    State.Loading -> binding.button.isEnabled = true
                    is State.Success -> {
                        binding.button.isEnabled = true
                        binding.message.text = state.dataPeople
                        Glide.with(requireActivity())
                            .load(state.urlPicture)
                            .into(binding.imageView)
                    }
                    is State.Error -> {
                        binding.button.isEnabled = true
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.error.collect {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
            }
        }

//        var urlPicture = ""
//        val apiInterface = ApiInterface.create().getPerson()
//
//
//        apiInterface.enqueue(object : Callback<People> {
//            override fun onResponse(call: Call<People>, response: Response<People>) {
//                val per = response.body()
//                val status = response.code()
//                if (per != null) {
//                    binding.message.text = per.results[0].toString()
//                }
//                if (per != null) {
//                    urlPicture = per.results[0].picture.getURL()
//                    Glide.with(requireActivity())
//                        .load(urlPicture)
//                        .into(binding.imageView)
//                }
//                Log.d("AAA", "${response.body()}")
//            }
//
//            override fun onFailure(call: Call<People>, t: Throwable) {
//                Log.d("AAA", "onFailure ${t.message}")
//            }
//
//        })


        binding.button.setOnClickListener {
            viewModel.getData()
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