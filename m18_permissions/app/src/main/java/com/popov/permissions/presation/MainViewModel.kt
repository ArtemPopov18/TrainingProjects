package com.popov.permissions.presation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popov.permissions.data.PhotoDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(private val photoDao: PhotoDao) : ViewModel() {

    val photo = this.photoDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )



}