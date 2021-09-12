package com.immortalalexsan.githubusers.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность для хранения данных о пользователе в таблице базы данных.
 *
 * @property id Идентификатор пользователя
 * @property avatarUrl Урл изображения.
 * @property login Логин.
 */
@Entity(tableName = UserEntity.TABLE_NAME)
internal data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0L,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String = "",

    @ColumnInfo(name = "login")
    var login: String = "",
) {

    companion object {
        /**
         * Имя таблицы в которой хранится сущность [UserEntity].
         */
        const val TABLE_NAME = "users_table"
    }
}
