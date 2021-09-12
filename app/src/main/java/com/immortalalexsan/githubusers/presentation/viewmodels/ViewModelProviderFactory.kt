package com.immortalalexsan.githubusers.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.immortalalexsan.core.converters.OneWayConverter
import com.immortalalexsan.core.schedulers.RxSchedulers
import com.immortalalexsan.githubusers.domain.interactors.UserMetadataInteractor
import com.immortalalexsan.githubusers.domain.interactors.UsersInteractor
import com.immortalalexsan.githubusers.domain.models.UserMetadataModel
import com.immortalalexsan.githubusers.domain.models.UserModel
import com.immortalalexsan.githubusers.presentation.models.UserMetadataModelItem
import com.immortalalexsan.githubusers.presentation.models.UserModelItem
import com.immortalalexsan.githubusers.presentation.usermetadata.UserMetadataViewModel
import com.immortalalexsan.githubusers.presentation.users.UsersViewModel
import javax.inject.Inject

/**
 * Фабрика для вью моделей.
 *
 * @property applicationContext Контекст приложения.
 * @property usersInteractor Интерактор для работы с данными пользователей.
 * @property userMetadataInteractor Интерактор для работы с метаданными пользователя.
 * @property schedulers Шедулеры для RX-цепочки.
 * @property userModelItemConverter Однонаправленный конвертер для [UserModel] и [UserModelItem].
 * @property userMetadataModelItemConverter Однонаправленный конвертер для [UserMetadataModel]
 * и [UserMetadataModelItem].
 */
internal class ViewModelProviderFactory @Inject constructor(
    private val applicationContext: Context,
    private val usersInteractor: UsersInteractor,
    private val userMetadataInteractor: UserMetadataInteractor,
    private val schedulers: RxSchedulers,
    private val userModelItemConverter: OneWayConverter<UserModel, UserModelItem>,
    private val userMetadataModelItemConverter: OneWayConverter<UserMetadataModel, UserMetadataModelItem>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            UsersViewModel::class.java -> UsersViewModel(
                applicationContext,
                usersInteractor,
                schedulers,
                userModelItemConverter
            ) as T
            UserMetadataViewModel::class.java -> UserMetadataViewModel(
                applicationContext,
                userMetadataInteractor,
                schedulers,
                userMetadataModelItemConverter
            ) as T
            else -> throw IllegalArgumentException("Not yet implemented")
        }
    }
}
