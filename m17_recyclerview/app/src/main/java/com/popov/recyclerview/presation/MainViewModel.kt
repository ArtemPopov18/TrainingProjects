package com.popov.recyclerview.presation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popov.recyclerview.data.MarsList
import com.popov.recyclerview.domain.GetMarsInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val getMarsInfoUseCase: GetMarsInfoUseCase) : ViewModel() {

    private val _mars = MutableStateFlow<List<MarsList>>(emptyList())
    val mars = _mars.asStateFlow()

    init {
        loadMars()
    }

    private fun loadMars() {
        viewModelScope.launch {
            kotlin.runCatching {
                getMarsInfoUseCase.getMarsInfo()
            }.fold(
                onSuccess = { _mars.value = it },
                onFailure = { Log.d("AAA", "Все упадо во вьюмодели") }
            )
        }
    }
}