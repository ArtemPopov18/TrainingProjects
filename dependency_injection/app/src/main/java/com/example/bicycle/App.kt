package com.example.bicycle

import android.app.Application
import com.example.bicycle.di.DaggerComponent
import com.example.bicycle.di.DaggerDaggerComponent
import com.example.bicycle.ui.main.BicycleFactory
import com.example.bicycle.ui.main.BicycleFrameFactory
import com.example.bicycle.ui.main.BicycleWheelDealer
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    companion object {
        lateinit var component: DaggerComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerDaggerComponent.builder().build()

        startKoin {
            modules(module {
                single { BicycleWheelDealer() }
                factory { BicycleFrameFactory() }
                factory { BicycleFactory(get(), get()) }
            })
        }
    }
}