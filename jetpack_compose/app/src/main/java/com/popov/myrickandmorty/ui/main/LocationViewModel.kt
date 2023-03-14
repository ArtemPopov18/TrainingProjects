package com.popov.myrickandmorty.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.popov.myrickandmorty.data.Result
import com.popov.myrickandmorty.domain.LocationPagingSource
import kotlinx.coroutines.flow.Flow

class LocationViewModel: ViewModel() {
    private val pagingSourceLocation = LocationPagingSource()

    val pagedLocation: Flow<PagingData<Result>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {pagingSourceLocation}
    ).flow.cachedIn(viewModelScope)
}
