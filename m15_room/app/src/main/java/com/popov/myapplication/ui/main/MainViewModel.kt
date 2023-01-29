package com.popov.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class MainViewModel(private val wordsDao: WordsDao) : ViewModel() {

    private val messageError = "Не допустимые символы"

    private val _state = MutableStateFlow<State>(State.Error(messageError))
    val state = _state.asStateFlow()
//    private val _error = Channel<String>()
//    val error = _error.receiveAsFlow()

    val words = this.wordsDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun stateButtonAdd(text: String) {
        if (!validity(text)) {
            _state.value = State.Error(messageError)
        } else {
            _state.value = State.Success
        }
    }

    fun validity(text: String): Boolean {
        var b = Pattern.compile("^[a-zA-Z-]+$").matcher(text).matches()
        Log.d("AAA", "$b")
        return text.isNotEmpty() && b
    }

    fun onAddButton(editText: String) {
        _state.value = State.Loading
        var isEditText = false
        var count = 1
        words.value.forEach {
            if (it.word == editText) {
                isEditText = true
                count = it.count + 1
            }
        }
        if (isEditText) {
            viewModelScope.launch {
                wordsDao.insert(
                    Words(
                        word = editText,
                        count = count
                    )
                )
                _state.value = State.Success
            }
        } else {
            viewModelScope.launch {
                wordsDao.insert(
                    Words(
                        word = editText,
                        count = 1
                    )
                )
                _state.value = State.Success
            }
        }
    }

    fun buttonClear() {
        _state.value = State.Loading
        viewModelScope.launch {
            wordsDao.delete()
            _state.value = State.Success
        }
    }

}