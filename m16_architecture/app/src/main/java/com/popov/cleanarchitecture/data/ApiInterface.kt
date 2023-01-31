package com.popov.cleanarchitecture.data
import com.popov.cleanarchitecture.entity.UsefulActivity
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("activity/")
    suspend fun getUsefulActivity(): UsefulActivity

    companion object {

        var BASE_URL = "https://www.boredapi.com/api/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}