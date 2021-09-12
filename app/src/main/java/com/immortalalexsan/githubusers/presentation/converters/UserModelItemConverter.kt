package com.immortalalexsan.githubusers.presentation.converters

import com.immortalalexsan.core.converters.OneWayConverter
import com.immortalalexsan.githubusers.domain.models.UserModel
import com.immortalalexsan.githubusers.presentation.models.UserModelItem

/**
 * Однонаправленный конвертер для [UserModel] и [UserModelItem].
 */
internal class UserModelItemConverter : OneWayConverter<UserModel, UserModelItem> {

    override fun convertTo(from: UserModel, applyBody: (UserModelItem.() -> UserModelItem)?): UserModelItem {
        val userModelItem = with(from) {
            UserModelItem(
                delegateItemId = id,
                avatarUrl = avatarUrl,
                id = id,
                login = login
            )
        }
        return applyBody?.invoke(userModelItem) ?: userModelItem
    }
}
