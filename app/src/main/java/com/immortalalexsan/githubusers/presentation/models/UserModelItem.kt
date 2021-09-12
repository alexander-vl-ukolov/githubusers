package com.immortalalexsan.githubusers.presentation.models

import com.immortalalexsan.core.delegates.BaseDelegateItem
import com.immortalalexsan.core.listeners.OnBaseDelegateItemClickListener

/**
 * Данные пользователя, которые будут использоваться во вьюхе.
 *
 * @property avatarUrl Урл изображения.
 * @property id Идентификатор пользователя.
 * @property login Логин.
 * @property onBaseDelegateItemClickListener Лиснер для клика по вьюхе, которая содержит данные объекта (опционально).
 */
internal data class UserModelItem(
    override val delegateItemId: Long,
    val avatarUrl: String,
    val id: Long,
    val login: String,
    var onBaseDelegateItemClickListener: OnBaseDelegateItemClickListener<UserModelItem>? = null,
) : BaseDelegateItem() {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (javaClass != other?.javaClass) {
            return false
        }

        other as UserModelItem

        if (delegateItemId != other.delegateItemId) {
            return false
        }

        return true
    }

    override fun hashCode(): Int {
        return delegateItemId.hashCode()
    }

    override fun toString(): String {
        return "UserModelItem(" +
            "delegateItemId=$delegateItemId, " +
            "avatarUrl='$avatarUrl', " +
            "id=$id, " +
            "login='$login', " +
            "onBaseDelegateItemClickListener=$onBaseDelegateItemClickListener" +
            ")"
    }
}
