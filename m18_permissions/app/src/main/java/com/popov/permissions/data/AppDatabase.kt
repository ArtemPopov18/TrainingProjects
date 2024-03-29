package com.popov.permissions.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.popov.permissions.entity.Photo

@Database(entities = [Photo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao():PhotoDao
}