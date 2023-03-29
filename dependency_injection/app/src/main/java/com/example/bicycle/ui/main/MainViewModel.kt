package com.example.bicycle.ui.main

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(val bicycleFactory: BicycleFactory) : ViewModel()