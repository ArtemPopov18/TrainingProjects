package com.popov.myapplication.ui.main

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {

    @Query("SELECT * FROM words LIMIT 5")
    fun getAll(): Flow<List<Words>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(words: Words)

    @Query("DELETE FROM words")
    suspend fun delete()
}