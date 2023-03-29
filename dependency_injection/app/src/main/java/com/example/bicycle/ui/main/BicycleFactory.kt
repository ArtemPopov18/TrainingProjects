package com.example.bicycle.ui.main

import javax.inject.Inject

class BicycleFactory @Inject constructor(
    private val frameFactory: BicycleFrameFactory,
    private val wheelDealer: BicycleWheelDealer,
    ) {

    private var bicycleSerialNumber = 0

    fun build(logo: String, color: Int): Bicycle {

        val wheelOne = wheelDealer.getWheel()
        val wheelTwo = wheelDealer.getWheel()
        val frame = frameFactory.getFrame(bicycleSerialNumber.toString(), color)

        bicycleSerialNumber += 1
        return Bicycle(
            listOf(wheelOne, wheelTwo),
            frame,
            logo)
    }
}