package com.immortalalexsan.githubusers.di

import android.content.Context
import com.immortalalexsan.core.di.CoreLibApi
import com.immortalalexsan.githubusers.core.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DataModule::class,
        DomainModule::class,
        PresentationModule::class,
        MainActivityModule::class
    ],
    dependencies = [
        CoreLibApi::class
    ]
)
internal interface ApplicationComponent : AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            coreLibApi: CoreLibApi,
        ): ApplicationComponent
    }
}
