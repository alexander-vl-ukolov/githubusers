package com.immortalalexsan.core.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Базовый дао.
 *
 * @param T Класс объекта, для которого будет использоваться этот дао.
 */
interface BaseDao<T> {

    fun select(): Single<List<T>>

    fun clear(): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(item: T): Single<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(items: List<T>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(item: T): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(items: List<T>): Single<List<Long>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAbort(item: T): Single<Long>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAbort(items: List<T>): Single<List<Long>>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateIgnore(item: T): Single<Int>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateIgnore(items: List<T>): Single<Int>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateReplace(item: T): Single<Int>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateReplace(items: List<T>): Single<Int>

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateAbort(item: T): Single<Int>

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateAbort(items: List<T>): Single<Int>

    @Delete
    fun delete(item: T): Single<Int>

    @Delete
    fun delete(items: List<T>): Single<Int>
}
