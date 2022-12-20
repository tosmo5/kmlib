package com.tosmo.kmlib.time.time

import com.tosmo.kmlib.time.zone.KZone

/**
 * 时间实现类
 *
 * @author Thomas Miao
 */
internal actual class KTimeImpl actual constructor(timeString: String, pattern: String) : KTimeBase {
    actual constructor(time: Long, zone: KZone) : this("","") {
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