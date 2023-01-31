package com.popov.cleanarchitecture.data

import android.util.Log
import com.popov.cleanarchitecture.entity.UsefulActivity

class UsefulActivitiesRepository {

    suspend fun getUsefulActivity(): UsefulActivity {
        Log.d("AAA", "Третий")
        val apiInterface = ApiInterface.create().getUsefulActivity()
        Log.d("AAA", "Четвертый")
        Log.d("AAA", "$apiInterface")
        val usefulActivityDto = UsefulActivityDto(
            accessibility = apiInterface.accessibility,
            activity = apiInterface.activity,
            key = apiInterface.key,
            link = apiInterface.link,
            participants = apiInterface.participants,
            price = apiInterface.price,
            type = apiInterface.type
        )
//        val usefulActivityDto = UsefulActivityDto(
//            accessibility = 0.5,
//            activity = "Бегать",
//            key = "Ключ",
//            link = "линк",
//            participants = 24,
//            price = 0.7,
//            type = "тайпе"
//        )
        Log.d("AAA", "$usefulActivityDto")
        return usefulActivityDto
    }
}

