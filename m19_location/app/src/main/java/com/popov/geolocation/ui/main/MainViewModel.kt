package com.popov.geolocation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _latLonModel = MutableStateFlow<List<LatLonModel>>(emptyList())
    val latLonModel = _latLonModel.asStateFlow()

    init {
        loadLatLonModel()
    }

    fun loadLatLonModel() {
        Log.d("AAA", "viewModel")
        _latLonModel.value = listOf(
            LatLonModel(56.4815, 84.9856, "Тут ничего нет интересного"),
            LatLonModel(56.4821, 84.9865, "И тут тоже ничего нет интересного")
        )
        Log.d("AAA", "${_latLonModel.value}")
    }
}