package com.popov.dataretetionbasick


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.popov.dataretetionbasick.databinding.FragmentSaveDateBinding

class SaveDataFragment : Fragment() {

    private var _binding: FragmentSaveDateBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSaveDateBinding.inflate(inflater, container, false)
        repository = Repository(requireContext())
        binding.buttonSave.setOnClickListener {
            repository.saveText(binding.editText.text.toString())
            binding.textField.text = repository.getText()
        }
        binding.buttonClear.setOnClickListener {
            repository.clearText()
            binding.editText.text = null
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}