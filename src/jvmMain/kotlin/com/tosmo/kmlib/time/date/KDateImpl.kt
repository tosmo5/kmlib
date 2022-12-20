package com.tosmo.kmlib.time.date

import com.tosmo.kmlib.time.JvmTimeUtils
import com.tosmo.kmlib.time.zone.KZone
import java.time.Instant
import java.time.LocalDate

/**
 * 日期实现类
 *
 * @author Thomas Miao
 */
internal actual class KDateImpl(val localDate: LocalDate) : KDateBase {

    actual constructor(dateString: String, pattern: String) : this(
        JvmTimeUtils.parseLocalDate(dateString, pattern)
    )

    actual constructor(date: Long, zone: KZone) : this(
        LocalDate.ofInstant(Instant.ofEpochMilli(date), zone.impl.zoneId)
    )

    override fun toEpochSecond(zone: KZone): Long = toDateTime().toEpochSecond(zone)

    override fun toEpochMilli(zone: KZone): Long = toDateTime().toEpochMilli(zone)

    override fun format(pattern: String): String {
        return JvmTimeUtils.format(localDate, pattern)
    }
}