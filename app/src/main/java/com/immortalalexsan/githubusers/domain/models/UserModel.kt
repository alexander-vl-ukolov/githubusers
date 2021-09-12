package com.immortalalexsan.githubusers.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Данные пользователя для общего списка.
 *
 * @property avatarUrl Урл изображения.
 * @property id Идентификатор пользователя.
 * @property login Логин.
 */
internal data class UserModel(
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("login")
    val login: String,
)
