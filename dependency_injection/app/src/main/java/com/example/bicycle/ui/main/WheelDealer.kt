package com.example.bicycle.ui.main

import javax.inject.Inject
import javax.inject.Singleton

interface WheelDealer {
    fun getWheel(): Wheel
}

@Singleton
class BicycleWheelDealer @Inject constructor() : WheelDealer {
    private var serialNumber = 0
    override fun getWheel(): Wheel {
        val wheel = Wheel(serialNumber.toString())
        serialNumber += 1
        return wheel
    }

}