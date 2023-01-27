package com.popov.retrofit.ui.main

import com.popov.retrofit.People
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("?results=1&inc=gender,name,location,bod,nat,picture")
    fun getPeople() : Call<People>

    companion object {

        var BASE_URL = "https://randomuser.me/api/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}