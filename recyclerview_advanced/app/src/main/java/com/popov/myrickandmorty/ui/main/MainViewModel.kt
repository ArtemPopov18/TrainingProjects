package com.popov.myrickandmorty.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.popov.myrickandmorty.domain.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val pagingSourceFactory = CharacterPagingSource()

    val pagedCharacter: Flow<PagingData<com.popov.myrickandmorty.data.Character>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { pagingSourceFactory }
    ).flow.cachedIn(viewModelScope)

}