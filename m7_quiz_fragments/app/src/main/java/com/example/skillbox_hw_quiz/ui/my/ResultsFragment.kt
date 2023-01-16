package com.example.skillbox_hw_quiz.ui.my

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentResultsBinding
import com.example.skillbox_hw_quiz.quiz.Quiz
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.*

class ResultsFragment : Fragment(R.layout.fragment_results) {

    private lateinit var binding: FragmentResultsBinding
    lateinit var listResults: List<Int>

    companion object {
        const val ARG_LIST_RESULTS = "results"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultsBinding.bind(view)

        listResults = requireArguments().getIntegerArrayList(ARG_LIST_RESULTS)!!

        if (Locale.getDefault().language == "ru"){
            addTextView(QuizStorage.getQuiz(QuizStorage.Locale.Ru))
        } else {
            addTextView(QuizStorage.getQuiz(QuizStorage.Locale.En))
        }

        binding.buttonStartOver.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun addTextView(list: Quiz) {
        list.questions.forEachIndexed { index, question ->
            val textView = TextView(requireContext())
            textView.text = question.feedback[listResults[index]]
            binding.scrollView.addView(textView)
    }
}
}