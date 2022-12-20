package com.tosmo.kmlib.time.datetime

import com.tosmo.kmlib.time.KTemporal
import com.tosmo.kmlib.time.date.KDate
import com.tosmo.kmlib.time.kDateCustomPattern
import com.tosmo.kmlib.time.kTimeCustomPattern
import com.tosmo.kmlib.time.time.KTime

/**
 * 日期基础接口
 * @author Thomas Miao
 */
internal interface KDateTimeBase : KTemporal {

    fun toDate(): KDate = KDate(toString(), kDateCustomPattern)

    fun toTime(): KTime = KTime(toString(), kTimeCustomPattern)
}