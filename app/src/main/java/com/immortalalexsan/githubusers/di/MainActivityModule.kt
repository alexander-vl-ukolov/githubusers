package com.immortalalexsan.githubusers.di

import com.immortalalexsan.githubusers.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    subcomponents = [
        MainActivitySubcomponent::class
    ]
)
internal abstract class MainActivityModule {

    @ContributesAndroidInjector(
        modules = [
            FragmentsModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}