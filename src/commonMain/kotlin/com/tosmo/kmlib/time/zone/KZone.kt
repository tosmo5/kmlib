package com.tosmo.kmlib.time.zone

/**
 *
 * @author Thomas Miao
 */
class KZone internal constructor(internal val impl: KZoneImpl) : KZoneBase by impl {

    companion object : KZoneBuilder by KZoneBuilderImpl
}