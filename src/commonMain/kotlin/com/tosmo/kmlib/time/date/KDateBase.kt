package com.tosmo.kmlib.time.date

import com.tosmo.kmlib.time.KTemporal
import com.tosmo.kmlib.time.datetime.KDateTime
import com.tosmo.kmlib.time.kDateTimeCustomPattern
import com.tosmo.kmlib.time.kTimeCustomPattern
import com.tosmo.kmlib.time.time.KTime

/**
 * 日期基础接口
 * @author Thomas Miao
 */
internal interface KDateBase : KTemporal {

    fun toDateTime(): KDateTime = KDateTime(toString(), kDateTimeCustomPattern)

    fun toTime(): KTime = KTime(toString(), kTimeCustomPattern)
}