package com.immortalalexsan.githubusers.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.immortalalexsan.githubusers.data.api.GithubApi
import com.immortalalexsan.githubusers.data.db.UsersDatabase
import com.immortalalexsan.githubusers.data.interceptors.RequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal class DataModule {

    @Provides
    @Singleton
    fun provideUsersDatabase(context: Context): UsersDatabase =
        UsersDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi {
        val gson = GsonBuilder()
            .setLenient()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()

        return retrofit.create(GithubApi::class.java)
    }

    private companion object {
        const val HOST = "https://api.github.com"
    }
}
