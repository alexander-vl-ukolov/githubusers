package com.immortalalexsan.githubusers.domain.interactors

import androidx.annotation.WorkerThread
import com.immortalalexsan.githubusers.domain.models.UserMetadataModel
import com.immortalalexsan.githubusers.domain.repositories.UserMetadataRepository
import io.reactivex.rxjava3.core.Single

/**
 * Интерактор для работы с метаданными пользователя.
 */
internal interface UserMetadataInteractor {

    /**
     * Запрашивает данные пользователя с именем [userName].
     *
     * @param userName Имя пользователя.
     * @return Данные пользователя с именем [userName].
     */
    @WorkerThread
    fun getUserMetadata(userName: String): Single<UserMetadataModel>
}

/**
 * Реализация [UserMetadataInteractor].
 *
 * @property repository Репозиторий для работы с метаданными пользователя.
 */
internal class UserMetadataInteractorImpl(private val repository: UserMetadataRepository) : UserMetadataInteractor {

    @WorkerThread
    override fun getUserMetadata(userName: String): Single<UserMetadataModel> {
        return repository.getUserMetadata(userName)
    }
}
