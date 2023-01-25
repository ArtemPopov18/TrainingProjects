package com.popov.mvvm.ui.main

sealed class State{
    object Loading: State()
    object Success: State()
    object ReadyToWork: State()
    data class Error(val editTextErrorMy: String?): State()
}
