package com.popov.permissions.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.popov.permissions.entity.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo")
    fun getAll(): Flow<List<Photo>>

    @Insert
    suspend fun insert(photo: Photo)

    @Query("DELETE FROM photo")
    suspend fun delete()
}