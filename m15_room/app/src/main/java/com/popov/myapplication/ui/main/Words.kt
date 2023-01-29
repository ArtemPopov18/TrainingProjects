package com.popov.myapplication.ui.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Words (
    @PrimaryKey
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "count")
    val count: Int
)