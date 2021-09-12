package com.immortalalexsan.githubusers.domain.repositories

import androidx.annotation.WorkerThread
import com.immortalalexsan.githubusers.domain.models.UserModel
import io.reactivex.rxjava3.core.Single

/**
 * Репозиторий для работы с данными пользователей.
 */
internal interface UsersRepository {

    /**
     * Запрашивает список пользователей.
     *
     * @param since Идентификатор пользователя, с которого нужно получить список.
     * @param perPage Количество пользователей на одну страницу.
     * @return Список пользователей.
     */
    @WorkerThread
    fun getUsers(since: Int, perPage: Int): Single<List<UserModel>>
}
