package com.popov.recyclerview.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("?sol=20&api_key=$API_KEY")
    suspend fun getPhotoMars(): List<Photo>

    companion object {

        private val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos"

        private const val API_KEY = "AcA2sJUXcXEgNQ7hH62NRsk9rUeabXiZg0K5r0Dx"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}