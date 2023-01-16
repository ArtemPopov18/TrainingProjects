package com.example.skillbox_hw_quiz.ui.my

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentQuizBinding
import com.example.skillbox_hw_quiz.quiz.Quiz
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.*

class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private lateinit var binding: FragmentQuizBinding
    var listBundle: MutableList<Int> = mutableListOf(0, 0, 0)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuizBinding.bind(view)

        if (Locale.getDefault().language == "ru") {
            addTextViewAndRadioGroup(QuizStorage.getQuiz(QuizStorage.Locale.Ru))
        } else {
            addTextViewAndRadioGroup(QuizStorage.getQuiz(QuizStorage.Locale.En))
        }


        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonSend.setOnClickListener {
            findNavController().navigate(
                R.id.action_quizFragment_to_resultsFragment,
                bundleOf(ResultsFragment.ARG_LIST_RESULTS to listBundle)
            )
        }
    }

    fun addTextViewAndRadioGroup(list: Quiz) {
        var numberRadioGroup = 0
        var numberRadioButton = 0
        list.questions.forEach {
            val textView = TextView(requireContext())
            textView.text = it.question
            binding.scrollView.addView(textView)

            val rg = RadioGroup(requireContext())
            rg.id = numberRadioGroup
            rg.orientation = RadioGroup.VERTICAL
            it.answers.forEach {
                val rb = RadioButton(requireContext())
                rb.id = numberRadioButton
                rg.addView(rb)
                rb.text = it
//                rb.setOnClickListener {
//
////                    listBundle.add(rg.id, rb.id)
//
//                }
                numberRadioButton = +1
            }
            rg.setOnCheckedChangeListener { _, i ->
                addListBundle(rg.id, i)
                Toast.makeText(activity, "$listBundle", Toast.LENGTH_SHORT).show()
            }
            binding.scrollView.addView(rg)
            numberRadioGroup = +1
        }
    }

    fun addListBundle(index: Int, number: Int) {
        listBundle[index] = number
    }
}