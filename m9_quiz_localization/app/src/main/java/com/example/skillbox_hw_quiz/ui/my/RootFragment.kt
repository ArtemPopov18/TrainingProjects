package com.example.skillbox_hw_quiz.ui.my

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentRootBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding
    private val calendar = Calendar.getInstance()
//    private val dateFormatRu = SimpleDateFormat("dd-MM-yy")
//    private val dateFormatEn = SimpleDateFormat("MM-dd-yy")
    private val dateFormat = if (Locale.getDefault().language == "ru") {
        SimpleDateFormat("dd-MM-yy")
    } else {
        SimpleDateFormat("MM-dd-yy")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)
//        binding = FragmentRootBinding.bind(requireView())   15 и 16 строка одинаковые использовать когда нет в методе view на вход

        binding.buttonProceed.setOnClickListener {
            findNavController().navigate(R.id.action_rootFragment_to_quizFragment)
        }

        binding.datePicker.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.choose_the_date_dialog_title))
                .build()

            dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                calendar.timeInMillis = timeInMillis
//                if (Locale.getDefault().language == "ru"){
//                    snackbarDatePicker(dateFormatRu)
//                } else {
//                    snackbarDatePicker(dateFormatEn)
//                }
                Snackbar.make(binding.datePicker, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
            }
            dateDialog.show(childFragmentManager, "DatePicker")
        }
    }

//    private fun snackbarDatePicker(dateFormat: SimpleDateFormat){
//        Snackbar.make(binding.datePicker, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
//    }
}