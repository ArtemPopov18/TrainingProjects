package com.popov.retrofit.ui.main

sealed class State{
    object Loading: State()
    data class Success(val dataPeople: String, val urlPicture: String): State()
    data class Error(val message: String): State()
}
