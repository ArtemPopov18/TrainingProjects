package com.popov.cleanarchitecture.domain

import android.util.Log
import com.popov.cleanarchitecture.data.UsefulActivitiesRepository
import com.popov.cleanarchitecture.entity.UsefulActivity

class GetUsefulActivityUseCase(
    private val usefulActivitiesRepository: UsefulActivitiesRepository
) {

    suspend fun execute() : UsefulActivity {
        Log.d("AAA", "Второй")
        val usefulActivity = usefulActivitiesRepository.getUsefulActivity()
        return usefulActivity
    }
}