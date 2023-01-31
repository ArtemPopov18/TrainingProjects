package com.popov.cleanarchitecture.domain

import android.util.Log
import com.popov.cleanarchitecture.data.UsefulActivitiesRepository
import com.popov.cleanarchitecture.data.UsefulActivityDto
import com.popov.cleanarchitecture.entity.UsefulActivity
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(
    private val usefulActivitiesRepository: UsefulActivitiesRepository
) {

    suspend fun execute() : UsefulActivity {
        Log.d("AAA", "Второй")
        val usefulActivityDto = usefulActivitiesRepository.getUsefulActivity()
        return usefulActivityDto
    }
}