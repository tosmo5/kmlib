package com.tosmo.kmlib.time

/**
 * 设置日期时间格式
 *
 * @see KTemporalPattern
 */
fun temporalSets(block: KTemporalConfiguration.() -> Unit) {
    block(KTemporalConfiguration)
}

/**
 * @see KTemporalConfiguration.datePattern
 */
val kDateCustomPattern
    get() = KTemporalConfiguration.datePattern

/**
 * @see KTemporalConfiguration.dateTimePattern
 */
val kDateTimeCustomPattern
    get() = KTemporalConfiguration.mDateTimePattern

/**
 * @see KTemporalConfiguration.timePattern
 */
val kTimeCustomPattern
    get() = KTemporalConfiguration.timePattern
