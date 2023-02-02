package com.popov.recyclerview.data

class MarsRepository {

    suspend fun getMars(): List<MarsList> {
        val marsList = ApiInterface.create().getPhotoMars()
        return marsList
    }
}