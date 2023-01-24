package com.popov.mvvm.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()
    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    fun onButtonClick(textEdit: String) {
        viewModelScope.launch {
            val countEditText = textEdit.count()
            if (countEditText < 3) {
                var editTextErrorMy: String? = null
                editTextErrorMy = "Длина строки меньше трех символов"
                _state.value = State.Error(editTextErrorMy)
                _error.send("Длина строки меньше трех символов")
            } else {
                _state.value = State.Loading
                repository.getData(textEdit)
                _state.value = State.Success
            }
        }
    }
}