package com.popov.myrickandmorty.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.popov.myrickandmorty.data.BaseRepository
import com.popov.myrickandmorty.data.Result

class LocationPagingSource(

) : PagingSource<Int, Result>() {
    private val baseRepository = BaseRepository()

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPage = params.key ?: 1

            val locationResponse = baseRepository.getLocationRep(nextPage)

            LoadResult.Page(
                data = locationResponse,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}