package com.immortalalexsan.githubusers.domain.interactors

import androidx.annotation.WorkerThread
import com.immortalalexsan.githubusers.domain.models.UserModel
import com.immortalalexsan.githubusers.domain.repositories.UsersRepository
import io.reactivex.rxjava3.core.Single

/**
 * Интерактор для работы с данными пользователей.
 */
internal interface UsersInteractor {

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

/**
 * Реализация [UsersInteractor].
 *
 * @property repository Репозиторий для работы с данными пользователей.
 */
internal class UsersInteractorImpl(private val repository: UsersRepository) : UsersInteractor {

    @WorkerThread
    override fun getUsers(since: Int, perPage: Int): Single<List<UserModel>> {
        return repository.getUsers(since, perPage)
    }
}
