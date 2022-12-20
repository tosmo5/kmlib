package com.tosmo.kmlib.time.date

import com.tosmo.kmlib.time.zone.KZone

/**
 * 日期实现类
 *
 * @author Thomas Miao
 */
internal actual class KDateImpl actual constructor(dateString: String, pattern: String) : KDateBase {

    actual constructor(date: Long, zone: KZone) : this("", "") {
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