package com.immortalalexsan.githubusers.presentation.users

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.immortalalexsan.core.converters.OneWayConverter
import com.immortalalexsan.core.listeners.OnBaseDelegateItemClickListener
import com.immortalalexsan.core.schedulers.RxSchedulers
import com.immortalalexsan.core.viewmodels.BaseViewModel
import com.immortalalexsan.githubusers.domain.interactors.UsersInteractor
import com.immortalalexsan.githubusers.domain.models.UserModel
import com.immortalalexsan.githubusers.presentation.models.UserModelItem
import io.reactivex.rxjava3.kotlin.addTo

/**
 * Вью модель для [UsersFragment].
 *
 * @property interactor Интерактор для работы с данными пользователей.
 * @property schedulers Шедулеры для RX-цепочки.
 * @property converter Однонаправленный конвертер для [UserModel] и [UserModelItem].
 * @constructor
 * Конструктор для вью модели.
 *
 * @param applicationContext Контекст приложения.
 */
internal class UsersViewModel(
    applicationContext: Context,
    private val interactor: UsersInteractor,
    private val schedulers: RxSchedulers,
    private val converter: OneWayConverter<UserModel, UserModelItem>,
) : BaseViewModel(applicationContext) {

    private val _userModelItems: MutableLiveData<List<UserModelItem>> = MutableLiveData()
    val userModelItems: LiveData<List<UserModelItem>> = _userModelItems

    private val _onUserModelItemClick: MutableLiveData<UserModelItem> = MutableLiveData()
    val onUserModelItemClick: LiveData<UserModelItem> = _onUserModelItemClick

    private val _onAsyncProcess: MutableLiveData<Boolean> = MutableLiveData()
    val onAsyncProcess: LiveData<Boolean> = _onAsyncProcess

    private val _onError: MutableLiveData<Throwable> = MutableLiveData()
    val onError: LiveData<Throwable> = _onError

    /**
     * Запрашивает список пользователей.
     */
    fun getUsers() {
        interactor.getUsers(SINCE_USER_ID, USERS_PER_PAGE)
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

    private fun onSuccess(userModels: List<UserModel>) {
        _userModelItems.value = userModels.map { userModel: UserModel ->
            converter.convertTo(userModel) {
                this.apply {
                    onBaseDelegateItemClickListener = object : OnBaseDelegateItemClickListener<UserModelItem> {
                        override fun onBaseDelegateItemClick(delegateItem: UserModelItem) {
                            _onUserModelItemClick.value = delegateItem
                        }
                    }
                }
            }
        }
    }

    private fun onError(throwable: Throwable) {
        _onError.value = throwable
    }

    private companion object {
        const val SINCE_USER_ID = 0
        const val USERS_PER_PAGE = 50
    }
}
