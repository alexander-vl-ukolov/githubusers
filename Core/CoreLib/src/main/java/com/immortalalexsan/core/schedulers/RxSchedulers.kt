package com.immortalalexsan.core.schedulers

import io.reactivex.rxjava3.core.Scheduler

/**
 * Шедулеры для RX-цепочки.
 */
interface RxSchedulers {

    val io: Scheduler
    val main: Scheduler
}
