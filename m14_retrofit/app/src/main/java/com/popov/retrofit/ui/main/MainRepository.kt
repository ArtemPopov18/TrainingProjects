package com.popov.retrofit.ui.main

import android.util.Log
import com.popov.retrofit.People
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {



    fun getData(): List<String>{
        var urlPicture = ""
        var dataPeople = ""
        var errorMessage: String? = null
        val apiInterface = ApiInterface.create().getPerson()
        val list = mutableListOf<String>()

        apiInterface.enqueue( object : Callback<People> {
            override fun onResponse(call: Call<People>, response: Response<People>) {
                val per = response.body()
                if (per != null) {
                    dataPeople = per.results[0].toString()
                }
                if (per != null) {
                    urlPicture = per.results[0].picture.getURL()
                }
                Log.d("AAA", "${response.body()}")
                list.add(0, dataPeople)
                list.add(1, urlPicture)
                Log.d("AAA", "лист данные 10 $list")
            }

            override fun onFailure(call: Call<People>, t: Throwable) {
//                Log.d("AAA", "onFailure ${t.message}")
                errorMessage = t.message
                list.add(errorMessage!!)
            }
        })

        return list
    }
}