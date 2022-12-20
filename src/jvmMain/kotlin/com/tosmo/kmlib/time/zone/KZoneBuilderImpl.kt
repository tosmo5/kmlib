package com.tosmo.kmlib.time.zone

import java.time.ZoneId

internal actual object KZoneBuilderImpl : KZoneBuilder {

    override val systemDefault: KZone
        get() = KZone(KZoneImpl(ZoneId.systemDefault()))

    override fun of(zone: String): KZone {
        return KZone(KZoneImpl(zone))
    }
}