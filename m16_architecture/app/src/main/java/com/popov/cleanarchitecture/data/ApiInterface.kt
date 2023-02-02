package com.popov.cleanarchitecture.data
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("activity/")
    suspend fun getUsefulActivity(): UsefulActivityDto

    companion object {

        var BASE_URL = "https://www.boredapi.com/api/"

        fun create() : ApiInterface {
            Log.d("AAA", "Пятый")
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}