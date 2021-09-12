package com.immortalalexsan.core.di

import android.content.Context
import com.immortalalexsan.core.resources.ResourceManager
import com.immortalalexsan.core.resources.ResourceManagerImpl
import com.immortalalexsan.core.schedulers.RxSchedulers
import com.immortalalexsan.core.schedulers.RxSchedulersImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class CoreLibModule {

    @Provides
    @Singleton
    fun provideRxSchedulers(): RxSchedulers =
        RxSchedulersImpl()

    @Provides
    @Singleton
    fun provideResourceManager(applicationContext: Context): ResourceManager =
        ResourceManagerImpl(applicationContext)
}
