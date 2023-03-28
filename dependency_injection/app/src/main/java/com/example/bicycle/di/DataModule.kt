package com.example.bicycle.di

import android.graphics.Color
import com.example.bicycle.ui.main.*
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    val logo = "Schoolboy"
    val color = Color.parseColor("#7FFF0000")

    @Provides
    fun frameFactory(): Frame {
        return Frame(color)
    }

    @Provides
    fun wheelDealer(): Wheel {
        return Wheel()
    }

    @Provides
    fun bicycleFactory(): Bicycle {
        return Bicycle()
    }

//     fun bicycle(): Bicycle {
//         return Bicycle()
//     }
}