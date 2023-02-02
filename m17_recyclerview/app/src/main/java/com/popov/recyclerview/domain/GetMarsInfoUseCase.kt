package com.popov.recyclerview.domain

import com.popov.recyclerview.data.MarsList
import com.popov.recyclerview.data.MarsRepository
import com.popov.recyclerview.data.Photo

class GetMarsInfoUseCase(
    private val marsRepository: MarsRepository
) {
    suspend fun getMarsInfo(): List<Photo>{
        val marsDateList = marsRepository.getMars()
        return marsDateList
    }
}