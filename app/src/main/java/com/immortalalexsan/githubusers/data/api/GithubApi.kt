package com.immortalalexsan.githubusers.data.api

import androidx.annotation.WorkerThread
import com.immortalalexsan.githubusers.domain.models.UserMetadataModel
import com.immortalalexsan.githubusers.domain.models.UserModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Интерфейс для REST API Github.
 */
internal interface GithubApi {

    /**
     * Запрашивает список пользователей.
     *
     * @param since Идентификатор пользователя, с которого нужно получить список.
     * @param perPage Количество пользователей на одну страницу.
     * @return Список пользователей.
     */
    @WorkerThread
    @GET("/users")
    fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int,
    ): Single<List<UserModel>>

    /**
     * Запрашивает данные пользователя с именем [userName].
     *
     * @param userName Имя пользователя.
     * @return Данные пользователя с именем [userName].
     */
    @WorkerThread
    @GET("/users/{username}")
    fun getUserMetadata(
        @Path("username") userName: String,
    ): Single<UserMetadataModel>
}
