package com.tosmo.kmlib.time.date

import com.tosmo.kmlib.time.zone.KZone

/**
 * 日期实现类
 *
 * @author Thomas Miao
 */
internal expect class KDateImpl(dateString: String, pattern: String) : KDateBase {

    constructor(date: Long, zone: KZone)
}