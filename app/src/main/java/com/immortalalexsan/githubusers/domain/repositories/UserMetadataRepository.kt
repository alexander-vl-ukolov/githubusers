package com.immortalalexsan.githubusers.domain.repositories

import androidx.annotation.WorkerThread
import com.immortalalexsan.githubusers.domain.models.UserMetadataModel
import io.reactivex.rxjava3.core.Single

/**
 * Репозиторий для работы с метаданными пользователя.
 */
internal interface UserMetadataRepository {

    /**
     * Запрашивает данные пользователя с именем [userName].
     *
     * @param userName Имя пользователя.
     * @return Данные пользователя с именем [userName].
     */
    @WorkerThread
    fun getUserMetadata(userName: String): Single<UserMetadataModel>
}
