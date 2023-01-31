package com.popov.cleanarchitecture.presation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val mainViewModel: MainViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            val usefulActivity = UsefulActivitiesRepository()
//            val useCase = GetUsefulActivityUseCase(usefulActivity)
//            return MainViewModel(useCase) as T
            return mainViewModel as T
        }
        throw IllegalArgumentException("Что то пошло не так в фабрике")
    }
}