package com.immortalalexsan.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreLibModule::class
    ]
)
interface CoreLibComponent : CoreLibApi {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): CoreLibComponent
    }
}
