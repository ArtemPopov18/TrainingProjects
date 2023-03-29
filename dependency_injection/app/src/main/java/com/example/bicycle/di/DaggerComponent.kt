package com.example.bicycle.di

import com.example.bicycle.ui.main.BicycleFactory
import com.example.bicycle.ui.main.BicycleFrameFactory
import com.example.bicycle.ui.main.BicycleWheelDealer
import com.example.bicycle.ui.main.MainFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DaggerModule::class],
)

interface DaggerComponent {
    fun bicycleFactory(): BicycleFactory

    fun injectMainFragment(fragment: MainFragment)
}

@Module
class DaggerModule {

    // можно так а можно на пряму указывать инджект в классе
//    @Provides
//    @Singleton
//    fun wheelDealer() = BicycleWheelDealer
//
//    @Provides
//    fun frameFactory() = BicycleFrameFactory()
//
//    @Provides
//    fun bicycleFactory() = BicycleFactory(frameFactory(), wheelDealer())
}