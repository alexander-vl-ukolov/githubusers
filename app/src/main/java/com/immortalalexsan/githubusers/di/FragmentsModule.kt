package com.immortalalexsan.githubusers.di

import com.immortalalexsan.githubusers.presentation.fragments.DefaultNavHostFragment
import com.immortalalexsan.githubusers.presentation.usermetadata.UserMetadataFragment
import com.immortalalexsan.githubusers.presentation.users.UsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeDefaultNavHostFragment(): DefaultNavHostFragment

    @ContributesAndroidInjector
    abstract fun contributeUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    abstract fun contributeUserMetadataFragment(): UserMetadataFragment
}
