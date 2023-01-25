package com.popov.mvvm.ui.main

import kotlinx.coroutines.delay

class MainRepository {
    suspend fun getData(textEdit: String): String{
        delay(7_000)
        return "По запросу $textEdit ничего не найдено"
    }
}