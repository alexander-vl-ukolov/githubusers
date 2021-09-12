package com.immortalalexsan.githubusers.data.repositories

import androidx.annotation.WorkerThread
import com.immortalalexsan.core.converters.TwoWayConverter
import com.immortalalexsan.githubusers.data.api.GithubApi
import com.immortalalexsan.githubusers.data.db.UsersDatabase
import com.immortalalexsan.githubusers.data.db.entities.UserEntity
import com.immortalalexsan.githubusers.domain.models.UserModel
import com.immortalalexsan.githubusers.domain.repositories.UsersRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.flatMapIterable

/**
 * Реализация [UsersRepository].
 *
 * @property api REST API Github.
 * @property database База данных пользователей.
 * @property converter Двунаправленный конвертер для [UserEntity] и [UserModel].
 */
internal class UsersRepositoryImpl(
    private val api: GithubApi,
    private val database: UsersDatabase,
    private val converter: TwoWayConverter<UserEntity, UserModel>,
) : UsersRepository {

    @WorkerThread
    override fun getUsers(since: Int, perPage: Int): Single<List<UserModel>> {
        return database.usersDao.getRecordsNumber()
            .flatMap { userRecordsCount: Int ->
                if (userRecordsCount == 0) {
                    getUserModelsFromNetwork(since, perPage)
                } else {
                    selectUserModelsFromDatabase()
                }
            }
    }

    private fun getUserModelsFromNetwork(since: Int, perPage: Int): Single<List<UserModel>> {
        val combiner = { userModels: List<UserModel>, _: List<Long> ->
            userModels
        }

        return api.getUsers(since, perPage)
            .flatMap(::insertUserModelsToDatabase, combiner)
    }

    private fun insertUserModelsToDatabase(userModels: List<UserModel>): Single<List<Long>> {
        val userEntities = userModels.map(converter::convertFrom)
        return database.usersDao.insertReplace(userEntities)
    }

    private fun selectUserModelsFromDatabase(): Single<List<UserModel>> =
        database.usersDao.select()
            .toObservable()
            .flatMapIterable()
            .map(converter::convertTo)
            .toList()
}
