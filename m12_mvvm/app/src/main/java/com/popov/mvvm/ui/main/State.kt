package com.popov.mvvm.ui.main

sealed class State{
    object Loading: State()
    data class Success(val editTextQuery: String?): State()
    object ReadyToWork: State()
    data class Error(val editTextErrorMy: String?): State()
}
