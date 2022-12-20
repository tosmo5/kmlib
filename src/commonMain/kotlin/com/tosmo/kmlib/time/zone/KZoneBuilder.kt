package com.tosmo.kmlib.time.zone

/**
 *
 * @author Thomas Miao
 */
internal interface KZoneBuilder {

    val systemDefault: KZone

    fun of(zone: String): KZone
}