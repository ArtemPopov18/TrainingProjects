package com.popov.myrickandmorty.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.popov.myrickandmorty.data.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterPagingSource : PagingSource<Int, com.popov.myrickandmorty.data.Character>() {
    private val repository = CharacterRepository()

    override fun getRefreshKey(state: PagingState<Int, com.popov.myrickandmorty.data.Character>): Int? = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.popov.myrickandmorty.data.Character> {
//        val page = params.key ?: FIRST_PAGE
//        return kotlin.runCatching {
//            repository.getCharacterRep(page)
//        }.fold(
//            onSuccess = {
//                LoadResult.Page(
//                    data = it,
//                    prevKey = null,
//                    nextKey = if (it.isEmpty()) null else page + 1
//                )
//            },
//            onFailure = { LoadResult.Error(it) }
//        )
        return try {
            val page = params.key ?: FIRST_PAGE
            val response = repository.getCharacterRep(page)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception){
            return LoadResult.Error(e)
        }
    }

    private companion object {
        private val FIRST_PAGE = 1
    }
}