package com.popov.recyclerview.data

import android.util.Log

class MarsRepository {

    suspend fun getMars(): List<Photo> {
        val apiInterface = ApiInterface.create().getPhotoMars()
        val listPhoto = apiInterface.photos
        return listPhoto
    }
}