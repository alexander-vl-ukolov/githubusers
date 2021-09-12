package com.immortalalexsan.githubusers.di

import android.content.Context
import com.immortalalexsan.githubusers.data.api.GithubApi
import com.immortalalexsan.githubusers.data.converters.UserEntityConverter
import com.immortalalexsan.githubusers.data.db.UsersDatabase
import com.immortalalexsan.githubusers.data.repositories.UserMetadataRepositoryImpl
import com.immortalalexsan.githubusers.data.repositories.UsersRepositoryImpl
import com.immortalalexsan.githubusers.domain.interactors.UserMetadataInteractor
import com.immortalalexsan.githubusers.domain.interactors.UserMetadataInteractorImpl
import com.immortalalexsan.githubusers.domain.interactors.UsersInteractor
import com.immortalalexsan.githubusers.domain.interactors.UsersInteractorImpl
import com.immortalalexsan.githubusers.domain.repositories.UserMetadataRepository
import com.immortalalexsan.githubusers.domain.repositories.UsersRepository
import dagger.Module
import dagger.Provides

@Module
internal class DomainModule {

    @Provides
    fun provideUsersRepository(api: GithubApi, context: Context): UsersRepository {
        val database = UsersDatabase.getInstance(context)
        val converter = UserEntityConverter()
        return UsersRepositoryImpl(api, database, converter)
    }

    @Provides
    fun provideUserMetadataRepository(api: GithubApi): UserMetadataRepository =
        UserMetadataRepositoryImpl(api)

    @Provides
    fun provideUsersInteractor(repository: UsersRepository): UsersInteractor =
        UsersInteractorImpl(repository)

    @Provides
    fun provideUserMetadataInteractor(repository: UserMetadataRepository): UserMetadataInteractor =
        UserMetadataInteractorImpl(repository)
}
