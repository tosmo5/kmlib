package com.tosmo.kmlib.time

/**
 * 日期和时间的生成接口
 *
 * @author Thomas Miao
 */
interface KTemporalBuilder<T> {

    /**
     * 取当前时刻
     */
    fun now(): T
}