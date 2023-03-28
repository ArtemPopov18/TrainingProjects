package com.example.bicycle.ui.main

import android.graphics.Color

interface FrameFactory {

    fun getFrame(serialNumber: String, color: Int): Frame
}

class BicycleFrameFactory : FrameFactory {
    override fun getFrame(serialNumber: String, color: Int): Frame {
        return Frame(serialNumber, color)
    }

}