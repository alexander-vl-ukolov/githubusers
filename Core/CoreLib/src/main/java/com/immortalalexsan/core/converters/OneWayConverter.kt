package com.immortalalexsan.core.converters

/**
 * Однонаправленный конвертер.
 *
 * @param F Из чего конвертируем.
 * @param T Во что конвертируем.
 */
interface OneWayConverter<F, T> {

    /**
     * Конвертирует из [F] в [T].
     *
     * @param from Экземпляр объекта класса [F].
     * @param applyBody Функциональный литерал с приемником, для дополнения выходного объекта типа [T] (опционально).
     * @return Экземпляр объекта класса [T].
     */
    fun convertTo(from: F, applyBody: (T.() -> T)? = null): T
}