package com.example.bicycle.ui.main

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(val bicycleFactory: BicycleFactory) : ViewModel() {

//    fun buildBicycle() {
//        val frameFactory = BicycleFrameFactory()
//        val wheelDealer = BicycleWheelDealer
//        val bicycleFactory = BicycleFactory(frameFactory, wheelDealer)
//        val bicycle = bicycleFactory.build("School", Color.parseColor("#7FFF0000"))
////        Log.d("AAA", "${bicycle.logo} ${bicycle.frame}")
//        val bicycle2 = bicycleFactory.build("252", Color.parseColor("#7FFF0000"))
////        Log.d("AAA", "${bicycle2.logo} ${bicycle2.frame}")
//    }
}