package com.immortalalexsan.githubusers.data.converters

import com.immortalalexsan.core.converters.TwoWayConverter
import com.immortalalexsan.githubusers.data.db.entities.UserEntity
import com.immortalalexsan.githubusers.domain.models.UserModel

/**
 * Двунаправленный конвертер для [UserEntity] и [UserModel].
 */
internal class UserEntityConverter : TwoWayConverter<UserEntity, UserModel> {

    override fun convertTo(from: UserEntity, applyBody: (UserModel.() -> UserModel)?): UserModel {
        return with(from) {
            UserModel(
                avatarUrl = avatarUrl,
                id = id,
                login = login
            )
        }
    }

    override fun convertFrom(to: UserModel, applyBody: (UserEntity.() -> UserModel)?): UserEntity {
        return with(to) {
            UserEntity(
                avatarUrl = avatarUrl,
                id = id,
                login = login
            )
        }
    }
}
