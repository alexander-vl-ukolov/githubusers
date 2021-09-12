package com.immortalalexsan.core.di

import com.immortalalexsan.core.resources.ResourceManager
import com.immortalalexsan.core.schedulers.RxSchedulers

/**
 * Апи ядра.
 */
interface CoreLibApi {

    fun getRxSchedulers(): RxSchedulers

    fun getResourceManager(): ResourceManager
}