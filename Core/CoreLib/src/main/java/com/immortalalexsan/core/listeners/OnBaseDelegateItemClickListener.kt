package com.immortalalexsan.core.listeners

import com.immortalalexsan.core.delegates.BaseDelegateItem

/**
 * Лиснер для клика по вьюхе, которая содержит данные объекта типа [T].
 *
 * @param T Класс объекта, который содержит данные.
 */
interface OnBaseDelegateItemClickListener<in T : BaseDelegateItem> {

    /**
     * Метод лиснера для клика по вьюхе, которая содержит данные объекта типа [T].
     *
     * @param delegateItem Объект, который содержит данные.
     */
    fun onBaseDelegateItemClick(delegateItem: T)
}
