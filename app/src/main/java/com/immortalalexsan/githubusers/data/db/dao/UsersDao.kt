package com.immortalalexsan.githubusers.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.immortalalexsan.core.db.BaseDao
import com.immortalalexsan.githubusers.data.db.entities.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Дао для таблицы пользователей.
 */
@Dao
internal interface UsersDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME}")
    override fun select(): Single<List<UserEntity>>

    @Query("DELETE FROM ${UserEntity.TABLE_NAME}")
    override fun clear(): Completable

    @Query("SELECT count(*) FROM ${UserEntity.TABLE_NAME}")
    fun getRecordsNumber(): Single<Int>
}
