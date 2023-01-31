package com.popov.cleanarchitecture.domain

import com.popov.cleanarchitecture.data.UsefulActivitiesRepository
import com.popov.cleanarchitecture.entity.UsefulActivity

class GetUsefulActivityUseCase(
    private val usefulActivitiesRepository: UsefulActivitiesRepository
) {

    suspend fun execute() : UsefulActivity {
        val usefulActivity = usefulActivitiesRepository.getUsefulActivity()
        return usefulActivity
    }
}