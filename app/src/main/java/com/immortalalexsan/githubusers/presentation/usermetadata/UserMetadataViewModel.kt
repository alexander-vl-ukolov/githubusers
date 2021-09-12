package com.immortalalexsan.githubusers.presentation.usermetadata

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.immortalalexsan.core.converters.OneWayConverter
import com.immortalalexsan.core.schedulers.RxSchedulers
import com.immortalalexsan.core.viewmodels.BaseViewModel
import com.immortalalexsan.githubusers.domain.interactors.UserMetadataInteractor
import com.immortalalexsan.githubusers.domain.models.UserMetadataModel
import com.immortalalexsan.githubusers.presentation.models.UserMetadataModelItem
import io.reactivex.rxjava3.kotlin.addTo

/**
 * Вью модель для [UserMetadataFragment].
 *
 * @property interactor Интерактор для работы с метаданными пользователя.
 * @property schedulers Шедулеры для RX-цепочки.
 * @property converter Однонаправленный конвертер для [UserMetadataModel] и [UserMetadataModelItem].
 * @constructor
 * Конструктор для вью модели.
 *
 * @param applicationContext Контекст приложения.
 */
internal class UserMetadataViewModel(
    applicationContext: Context,
    private val interactor: UserMetadataInteractor,
    private val schedulers: RxSchedulers,
    private val converter: OneWayConverter<UserMetadataModel, UserMetadataModelItem>,
) : BaseViewModel(applicationContext) {

    private val _userMetadataModelItem: MutableLiveData<UserMetadataModelItem> = MutableLiveData()
    val userMetadataModelItem: LiveData<UserMetadataModelItem> = _userMetadataModelItem

    private val _onAsyncProcess: MutableLiveData<Boolean> = MutableLiveData()
    val onAsyncProcess: LiveData<Boolean> = _onAsyncProcess

    private val _onError: MutableLiveData<Throwable> = MutableLiveData()
    val onError: LiveData<Throwable> = _onError

    /**
     * Запрашивает данные пользователя с именем [userName].
     *
     * @param userName Имя пользователя.
     */
    fun getUserMetadata(userName: String) {
        interactor.getUserMetadata(userName)
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.main)
            .doOnSubscribe {
                onAsyncProcessInProgress(true)
            }
            .doOnTerminate {
                onAsyncProcessInProgress(false)
            }
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onAsyncProcessInProgress(asyncProcessInProgress: Boolean) {
        _onAsyncProcess.value = asyncProcessInProgress
    }

    private fun onSuccess(userMetadataModel: UserMetadataModel) {
        val userMetadataModelItem = converter.convertTo(userMetadataModel)
        _userMetadataModelItem.value = userMetadataModelItem
    }

    private fun onError(throwable: Throwable) {
        _onError.value = throwable
    }
}
