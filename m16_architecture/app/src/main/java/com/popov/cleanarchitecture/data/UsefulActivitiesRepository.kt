package com.popov.cleanarchitecture.data

import android.util.Log
import com.popov.cleanarchitecture.entity.UsefulActivity
import javax.inject.Inject

class UsefulActivitiesRepository @Inject constructor() {

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
        Log.d("AAA", "$usefulActivityDto")
        return usefulActivityDto
    }
}

