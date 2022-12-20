package com.tosmo.kmlib.time.datetime

import com.tosmo.kmlib.time.JTimeUtils
import com.tosmo.kmlib.time.zone.KZone
import java.time.Instant
import java.time.LocalDateTime

/**
 * 时间日期实现类
 *
 * @author Thomas Miao
 */
internal actual class KDateTimeImpl(val localDateTime: LocalDateTime) : KDateTimeBase {

    actual constructor(dateTimeString: String, pattern: String) : this(
        JTimeUtils.parseLocalDateTime(dateTimeString, pattern)
    )

    actual constructor(dateTime: Long, zone: KZone) : this(
        LocalDateTime.ofInstant(Instant.ofEpochMilli(dateTime), zone.impl.zoneId)
    ) {
        localDateTime.atZone(zone.impl.zoneId).toEpochSecond()
    }

    override fun toEpochSecond(zone: KZone): Long = localDateTime.atZone(zone.impl.zoneId).toEpochSecond()

    override fun toEpochMilli(zone: KZone): Long = Instant.ofEpochSecond(toEpochSecond(zone)).toEpochMilli()

    override fun format(pattern: String): String {
        return JTimeUtils.format(localDateTime, pattern)
    }

}