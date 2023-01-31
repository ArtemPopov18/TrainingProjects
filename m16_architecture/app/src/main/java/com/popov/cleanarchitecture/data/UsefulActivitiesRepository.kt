package com.popov.cleanarchitecture.data

import com.popov.cleanarchitecture.entity.UsefulActivity

class UsefulActivitiesRepository {

    suspend fun getUsefulActivity(): UsefulActivity {

        val apiInterface = ApiInterface.create().getUsefulActivity()
        val usefulActivityDto = UsefulActivityDto(
            accessibility = apiInterface.accessibility,
            activity = apiInterface.activity,
            key = apiInterface.key,
            link = apiInterface.link,
            participants = apiInterface.participants,
            price = apiInterface.price,
            type = apiInterface.type
        )
        return usefulActivityDto
    }
}

