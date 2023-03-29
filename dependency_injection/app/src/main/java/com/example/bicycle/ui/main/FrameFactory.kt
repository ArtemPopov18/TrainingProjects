package com.example.bicycle.ui.main

import android.graphics.Color
import javax.inject.Inject

interface FrameFactory {

    fun getFrame(serialNumber: String, color: Int): Frame
}

class BicycleFrameFactory @Inject constructor() : FrameFactory {
    override fun getFrame(serialNumber: String, color: Int): Frame {
        return Frame(serialNumber, color)
    }

}