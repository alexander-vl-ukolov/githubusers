package com.immortalalexsan.githubusers.di

import com.immortalalexsan.githubusers.presentation.main.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
internal interface MainActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>
}