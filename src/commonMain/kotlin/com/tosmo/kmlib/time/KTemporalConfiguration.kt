package com.tosmo.kmlib.time

import com.tosmo.kmlib.time.date.KDate
import com.tosmo.kmlib.time.datetime.KDateTime
import com.tosmo.kmlib.time.time.KTime

/**
 * 日期时间设置
 *
 * @author Thomas Miao
 */
object KTemporalConfiguration {

    internal var mDatePattern: String = KTemporalPattern.DEFAULT_DATE_PATTERN

    /**
     * 日期格式
     *
     * @see KDate
     * @see KTemporalPattern
     */
    val datePattern
        get() = mDatePattern

    internal var mDateTimePattern: String = KTemporalPattern.DEFAULT_DATE_TIME_PATTERN

    /**
     * 日期时间格式
     *
     * @see KDateTime
     * @see KTemporalPattern
     */
    val dateTimePattern
        get() = mDateTimePattern

    internal var mTimePattern: String = KTemporalPattern.DEFAULT_TIME_PATTERN

    /**
     * 时间格式
     *
     * @see KTime
     * @see KTemporalPattern
     */
    val timePattern
        get() = mTimePattern

}