package com.immortalalexsan.githubusers.data.repositories

import androidx.annotation.WorkerThread
import com.immortalalexsan.githubusers.data.api.GithubApi
import com.immortalalexsan.githubusers.domain.models.UserMetadataModel
import com.immortalalexsan.githubusers.domain.repositories.UserMetadataRepository
import io.reactivex.rxjava3.core.Single

/**
 * Реализация [UserMetadataRepository].
 *
 * @property api REST API Github.
 */
internal class UserMetadataRepositoryImpl(private val api: GithubApi) : UserMetadataRepository {

    @WorkerThread
    override fun getUserMetadata(userName: String): Single<UserMetadataModel> {
        return api.getUserMetadata(userName)
    }
}
