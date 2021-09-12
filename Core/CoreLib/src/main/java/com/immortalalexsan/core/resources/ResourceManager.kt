package com.immortalalexsan.core.resources

import androidx.annotation.StringRes

/**
 * Менеджер ресурсов.
 */
interface ResourceManager {

    /**
     * Получает строку из [stringResId].
     *
     * @param stringResId Идентификатор ресурса строки.
     * @return Строка.
     */
    fun getString(@StringRes stringResId: Int): String
}
