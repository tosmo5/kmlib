package com.tosmo.kmlib.time.time

import com.tosmo.kmlib.time.JvmTimeUtils
import com.tosmo.kmlib.time.zone.KZone
import java.time.Instant
import java.time.LocalTime

/**
 * 时间实现类
 *
 * @author Thomas Miao
 */
internal actual class KTimeImpl(val localTime: LocalTime) : KTimeBase {

    actual constructor(timeString: String, pattern: String) : this(
        JvmTimeUtils.parseLocalTime(timeString, pattern)
    )

    actual constructor(time: Long, zone: KZone) : this(
        LocalTime.ofInstant(Instant.ofEpochMilli(time), zone.impl.zoneId)
    )

    override fun toEpochSecond(zone: KZone): Long = toDateTime().toEpochSecond(zone)

    override fun toEpochMilli(zone: KZone): Long = toDateTime().toEpochMilli(zone)

    override fun format(pattern: String): String {
        return JvmTimeUtils.format(localTime, pattern)
    }
}