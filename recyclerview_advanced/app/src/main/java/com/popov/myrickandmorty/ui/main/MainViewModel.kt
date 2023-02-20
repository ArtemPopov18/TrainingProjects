package com.popov.myrickandmorty.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.popov.myrickandmorty.domain.CharacterPagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    val pagedCharacter: Flow<PagingData<com.popov.myrickandmorty.data.Character>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { CharacterPagingSource() }
    ).flow.cachedIn(viewModelScope)

}