package com.popov.retrofit.ui.main

import android.util.Log
import com.popov.retrofit.People
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    fun getData(callBackListData: (List<String>) -> Unit) {
        var urlPicture = ""
        var dataPeople = ""
        var errorMessage: String? = null
        val apiInterface = ApiInterface.create().getPeople()
        val list = mutableListOf<String>()

        apiInterface.enqueue(object : Callback<People> {
            override fun onResponse(call: Call<People>, response: Response<People>) {
                val per = response.body()
                if (per != null) {
                    dataPeople = per.results[0].toString()
                    urlPicture = per.results[0].picture.getURL()
                }
                Log.d("AAA", "${response.body()}")
                list.add(0, dataPeople)
                list.add(1, urlPicture)
                callBackListData(list)
                Log.d("AAA", "лист данные 10 $list")
            }

            override fun onFailure(call: Call<People>, t: Throwable) {
//                Log.d("AAA", "onFailure ${t.message}")
                errorMessage = t.message
                list.add(errorMessage!!)
                callBackListData(list)
            }
        })
    }
}