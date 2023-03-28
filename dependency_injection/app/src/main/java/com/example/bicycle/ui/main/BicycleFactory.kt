package com.example.bicycle.ui.main

class BicycleFactory(frameFactory: FrameFactory, wheelDealer: WheelDealer) {
    private val wheelDealer = wheelDealer

    private val frameFactory = frameFactory

    private var bicycleSerialNumber = 0

    fun build(logo: String, color: Int): Bicycle {
        val wheelOne = wheelDealer.getWheel()
        val wheelTwo = wheelDealer.getWheel()
        val frame = frameFactory.getFrame(bicycleSerialNumber.toString(), color)

        bicycleSerialNumber += 1
        return Bicycle(listOf(wheelOne, wheelTwo), frame, logo)
    }
}