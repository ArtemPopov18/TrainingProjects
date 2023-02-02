package com.popov.recyclerview.domain

import com.popov.recyclerview.data.MarsList
import com.popov.recyclerview.data.MarsRepository

class GetMarsInfoUseCase(
    private val marsRepository: MarsRepository
) {
    suspend fun getMarsInfo(): List<MarsList>{
        val marsDateList = marsRepository.getMars()
        return marsDateList
    }
}