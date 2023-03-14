package com.popov.myrickandmorty.data

import retrofit2.Response

class BaseRepository() {

    suspend fun getLocationRep(page: Int): List<Result> {
        val apiInterface = ApiInterface.create().getLocation(page)
        return apiInterface?.body()?.results ?: emptyList()
    }

}