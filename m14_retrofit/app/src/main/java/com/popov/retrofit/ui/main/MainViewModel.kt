package com.popov.retrofit.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()
    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    fun getData() {
        _state.value = State.Loading
        repository.getData(::resultCollBackListData)
    }

    fun resultCollBackListData(list: List<String>){
        if (list.lastIndex == 1) {
            _state.value = State.Success(list[0], list[1])
        } else {
            _state.value = State.Error(list[0])
        }
    }
}