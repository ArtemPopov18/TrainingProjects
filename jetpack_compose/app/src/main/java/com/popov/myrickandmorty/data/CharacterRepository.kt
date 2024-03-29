package com.popov.myrickandmorty.data

import android.util.Log

class CharacterRepository {

    suspend fun getCharacterRep(page: Int): List<Character> {
        val apiInterface = ApiInterface.create().getCharacter(page)
        Log.d("AAA", "${apiInterface?.body()?.results}")
        Log.d("AAA", "${apiInterface.code()}")
        return apiInterface?.body()?.results ?: emptyList()
    }
}