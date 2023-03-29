package com.example.bicycle.ui.main

class Bicycle(private val wheelList: List<Wheel>, private val frame: Frame, private val logo: String) {
    override fun toString(): String {

        val info = "$logo\n${frame.color}\n${frame.serialNumber}\n${wheelList[0].serialNumber}\n" +
                "${wheelList[1].serialNumber}"

        return info
    }
}