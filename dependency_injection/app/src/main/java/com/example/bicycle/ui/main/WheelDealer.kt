package com.example.bicycle.ui.main

interface WheelDealer {
    fun getWheel(): Wheel
}

object BicycleWheelDealer : WheelDealer {
    private var serialNumber = 0
    override fun getWheel(): Wheel {
        val wheel = Wheel(serialNumber.toString())
        serialNumber += 1
        return wheel
    }

}