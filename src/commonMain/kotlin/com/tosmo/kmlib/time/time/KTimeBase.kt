package com.tosmo.kmlib.time.time

import com.tosmo.kmlib.time.KTemporal
import com.tosmo.kmlib.time.date.KDate
import com.tosmo.kmlib.time.datetime.KDateTime
import com.tosmo.kmlib.time.kDateCustomPattern
import com.tosmo.kmlib.time.kDateTimeCustomPattern

/**
 * 日期基础接口
 * @author Thomas Miao
 */
internal interface KTimeBase : KTemporal {

    fun toDate(): KDate = KDate(toString(), kDateCustomPattern)

    fun toDateTime(): KDateTime = KDateTime(toString(), kDateTimeCustomPattern)
}