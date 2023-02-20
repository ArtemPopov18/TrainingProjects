package com.popov.myrickandmorty.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.popov.myrickandmorty.data.CharacterRepository

class CharacterPagingSource : PagingSource<Int, com.popov.myrickandmorty.data.Character>() {
    private val repository = CharacterRepository()

    override fun getRefreshKey(state: PagingState<Int, com.popov.myrickandmorty.data.Character>): Int? = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.popov.myrickandmorty.data.Character> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getCharacterRep(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    private companion object {
        private val FIRST_PAGE = 1
    }
}