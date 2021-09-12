package com.immortalalexsan.core.converters

/**
 * Двунаправленный конвертер.
 *
 * @param F Из чего / во что конвертируем.
 * @param T Во что / из чего конвертируем.
 */
interface TwoWayConverter<F, T> : OneWayConverter<F, T> {

    /**
     * Конвертирует из [T] в [F].
     *
     * @param to Экземпляр объекта класса [T].
     * @param applyBody Функциональный литерал с приемником, для дополнения выходного объекта типа [F] (опционально).
     * @return Экземпляр объекта класса [F].
     */
    fun convertFrom(to: T, applyBody: (F.() -> T)? = null): F
}
