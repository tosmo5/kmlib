package com.tosmo.kmlib.time.datetime

import com.tosmo.kmlib.time.zone.KZone

/**
 * 时间日期实现类
 *
 * @author Thomas Miao
 */
internal expect class KDateTimeImpl(dateTimeString: String, pattern: String) : KDateTimeBase {

    constructor(dateTime: Long, zone: KZone)
}