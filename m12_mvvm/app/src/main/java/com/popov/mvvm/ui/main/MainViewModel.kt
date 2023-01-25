package com.popov.mvvm.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.ReadyToWork)
    val state = _state.asStateFlow()
    private val _stateIsEnabled = MutableStateFlow(false)
    val stateIsEnabled = _stateIsEnabled.asStateFlow()
    private val _stateChannel = Channel<String>()
    val stateChannel = _stateChannel.receiveAsFlow()

    fun onButtonClick(textEdit: String) {
        viewModelScope.launch {
            var countEditText = textEdit.count()
            var editTextErrorMy: String? = null
            if (countEditText < 3) {
                delay(2000)
                editTextErrorMy = "Длина строки меньше трех символов"
                _state.value = State.Error(editTextErrorMy)
                _stateChannel.send("Ошибка в запросе")
            } else {
                _state.value = State.Loading
                _state.value = State.Success(repository.getData(textEdit))}
        }
    }

    fun stateButton(value: Boolean){
        _stateIsEnabled.value = value
    }

//    fun countTextEdit(textEdit: String){
//        viewModelScope.launch {
//            var countEditText = textEdit.count()
//            var editTextErrorMy: String? = null
//            if (countEditText < 3) {
//                delay(2000)
//                editTextErrorMy = "Длина строки меньше трех символов"
//                _state.value = State.Error(editTextErrorMy)
//                _stateChannel.send("Ошибка в запросе")
//            } else {
//                _state.value = State.ReadyToWork
//            }
//        }
//    }
}