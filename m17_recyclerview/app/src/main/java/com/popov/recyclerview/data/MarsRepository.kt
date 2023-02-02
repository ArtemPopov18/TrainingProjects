package com.popov.recyclerview.data

import android.util.Log

class MarsRepository {

    suspend fun getMars(): List<Photo> {
        val marsList = ApiInterface.create().getPhotoMars()
        Log.d("AAA", "$marsList")
        return marsList
    }
}