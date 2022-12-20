package com.tosmo.kmlib.time

internal val temporalConfiguration = KTemporalConfiguration()

/**
 * 设置日期时间格式
 *
 * @see KTemporalPattern
 */
fun temporalSets(block: KTemporalConfiguration.() -> Unit) {
    block(temporalConfiguration)
}

/**
 * @see KTemporalConfiguration.datePattern
 */
val kDateCustomPattern
    get() = temporalConfiguration.datePattern

/**
 * @see KTemporalConfiguration.dateTimePattern
 */
val kDateTimeCustomPattern
    get() = temporalConfiguration.mDateTimePattern

/**
 * @see KTemporalConfiguration.timePattern
 */
val kTimeCustomPattern
    get() = temporalConfiguration.timePattern
