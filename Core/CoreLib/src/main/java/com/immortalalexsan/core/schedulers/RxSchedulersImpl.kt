package com.immortalalexsan.core.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Реализация [RxSchedulers].
 */
class RxSchedulersImpl : RxSchedulers {

    override val io: Scheduler
        get(): Scheduler = Schedulers.io()

    override val main: Scheduler
        get(): Scheduler = AndroidSchedulers.mainThread()
}
