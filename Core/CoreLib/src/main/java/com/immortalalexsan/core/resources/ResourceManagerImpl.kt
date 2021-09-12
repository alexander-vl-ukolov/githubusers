package com.immortalalexsan.core.resources

import android.content.Context
import java.lang.ref.WeakReference

/**
 * Реализация менеджера ресурсов.
 *
 * @param applicationContext Контекст приложения.
 */
class ResourceManagerImpl(applicationContext: Context) : ResourceManager {

    private val context: WeakReference<Context> = WeakReference(applicationContext.applicationContext)

    override fun getString(stringResId: Int): String {
        return getApplicationContext().getString(stringResId)
    }

    private fun getApplicationContext(): Context =
        requireNotNull(context.get())
}
