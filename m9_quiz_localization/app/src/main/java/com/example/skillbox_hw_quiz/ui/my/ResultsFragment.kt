package com.example.skillbox_hw_quiz.ui.my

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import androidx.activity.addCallback
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_resultsFragment_to_rootFragment)
        }
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
            textView.text = question.feedback[listResults[index] - 10]
            binding.scrollView.addView(textView)

            ObjectAnimator.ofArgb(textView,
            "textColor",
            Color.parseColor("#FFFF0000"),
            Color.parseColor("#FF388E3C")
            ).apply {
                duration = 3000
                interpolator = AccelerateDecelerateInterpolator()
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
            }.start()
    }
}
}