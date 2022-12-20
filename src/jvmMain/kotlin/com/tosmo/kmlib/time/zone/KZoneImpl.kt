package com.tosmo.kmlib.time.zone

import java.time.ZoneId

/**
 *
 * @author Thomas Miao
 */
internal actual class KZoneImpl(val zoneId: ZoneId) : KZoneBase {

    actual constructor(zoneId: String) : this(ZoneId.of(zoneId))

    override val id: String
        get() = zoneId.id
}