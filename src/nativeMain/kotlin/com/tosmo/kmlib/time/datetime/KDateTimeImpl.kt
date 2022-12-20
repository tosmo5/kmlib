package com.tosmo.kmlib.time.datetime

import com.tosmo.kmlib.time.zone.KZone

/**
 * 时间日期实现类
 *
 * @author Thomas Miao
 */
internal actual class KDateTimeImpl actual constructor(dateTimeString: String, pattern: String) :
    KDateTimeBase {
    actual constructor(dateTime: Long, zone: KZone) : this("","") {
        TODO("Not yet implemented")
    }

    override fun toEpochSecond(zone: KZone): Long {
        TODO("Not yet implemented")
    }

    override fun toEpochMilli(zone: KZone): Long {
        TODO("Not yet implemented")
    }

    override fun format(pattern: String): String {
        TODO("Not yet implemented")
    }

}