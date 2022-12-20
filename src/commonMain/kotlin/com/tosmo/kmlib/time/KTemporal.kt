package com.tosmo.kmlib.time

import com.tosmo.kmlib.time.zone.KZone

/**
 * 时间接口
 *
 * @author Thomas Miao
 */
internal interface KTemporal {

    fun toEpochSecond(zone: KZone): Long

    fun toEpochMilli(zone: KZone): Long

    /**
     * 返回[pattern]格式的字符串
     *
     * @param pattern 默认为""
     */
    fun format(pattern: String): String
}