package com.immortalalexsan.githubusers.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Метаданные пользователя.
 *
 * @property avatarUrl Урл изображения.
 * @property blog Блог (опционально).
 * @property company Компания (опционально).
 * @property location Местоположение (опционально).
 * @property email Электронная почта (опционально).
 * @property login Логин.
 * @property name Имя (опционально).
 */
internal data class UserMetadataModel(
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @Expose
    @SerializedName("blog")
    val blog: String?,

    @Expose
    @SerializedName("company")
    val company: String?,

    @Expose
    @SerializedName("location")
    val location: String?,

    @Expose
    @SerializedName("email")
    val email: String?,

    @Expose
    @SerializedName("login")
    val login: String,

    @Expose
    @SerializedName("name")
    val name: String?,
)
