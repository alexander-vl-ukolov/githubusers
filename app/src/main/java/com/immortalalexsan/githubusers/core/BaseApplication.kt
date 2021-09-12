package com.immortalalexsan.githubusers.core

import com.immortalalexsan.core.di.DaggerCoreLibComponent
import com.immortalalexsan.githubusers.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Базовый класс приложения.
 */
internal class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val coreLibApi = DaggerCoreLibComponent.factory().create(applicationContext)
        return DaggerApplicationComponent.factory().create(applicationContext, coreLibApi)
    }
}
