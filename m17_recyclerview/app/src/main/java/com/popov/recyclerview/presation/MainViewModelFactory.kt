package com.popov.recyclerview.presation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.popov.recyclerview.data.MarsRepository
import com.popov.recyclerview.domain.GetMarsInfoUseCase

class MainViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val marsRepository = MarsRepository()
        val useCase = GetMarsInfoUseCase(marsRepository)

        return MainViewModel(useCase) as T
    }
}