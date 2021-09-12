package com.immortalalexsan.githubusers.presentation.models

/**
 * Метаданные пользователя, которые будут использоваться во вьюхе.
 *
 * @property avatarUrl Урл изображения.
 * @property blogFormatted Отформатированный блог.
 * @property companyFormatted Отформатированная компания.
 * @property locationFormatted Отформатированное местоположение.
 * @property emailFormatted Отформатированная электронная почта.
 * @property loginFormatted Отформатированный логин.
 * @property nameFormatted Отформатированное имя.
 */
internal data class UserMetadataModelItem(
    val avatarUrl: String,
    val blogFormatted: String,
    val companyFormatted: String,
    val locationFormatted: String,
    val emailFormatted: String,
    val loginFormatted: String,
    val nameFormatted: String,
)
