package com.example.skillbox_hw_quiz.ui.my

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentRootBinding

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)
//        binding = FragmentRootBinding.bind(requireView())   15 и 16 строка одинаковые использовать когда нет в методе view на вход

        binding.buttonProceed.setOnClickListener {
            findNavController().navigate(R.id.action_rootFragment_to_quizFragment)
        }
    }
}