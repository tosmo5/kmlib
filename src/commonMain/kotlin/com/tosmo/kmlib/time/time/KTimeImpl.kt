package com.tosmo.kmlib.time.time

import com.tosmo.kmlib.time.zone.KZone


/**
 * 时间实现类
 *
 * @author Thomas Miao
 */
internal expect class KTimeImpl(timeString: String, pattern: String) : KTimeBase {

    constructor(time: Long, zone: KZone)
}