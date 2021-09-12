package com.immortalalexsan.core.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Базовая вью модель.
 *
 * @constructor
 * Создает базовую вью модель.
 *
 * @param applicationContext Контекст приложения.
 */
abstract class BaseViewModel(applicationContext: Context) : ViewModel() {

    private val _applicationContext: WeakReference<Context> = WeakReference(applicationContext.applicationContext)
    protected val applicationContext: Context
        get(): Context = _applicationContext.get()!!

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
}
