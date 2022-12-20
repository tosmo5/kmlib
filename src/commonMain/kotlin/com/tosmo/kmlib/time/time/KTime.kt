package com.tosmo.kmlib.time.time

import com.tosmo.kmlib.time.*
import com.tosmo.kmlib.time.zone.KZone
import kotlinx.serialization.Serializable

/**
 *
 * @author Thomas Miao
 */
@Serializable(KTimeAsStringSerializer::class)
class KTime internal constructor(internal val impl: KTimeImpl) : KTimeBase by impl {

    constructor(timeString: String, pattern: String = "") : this(
        KTimeImpl(timeString, pattern.ifEmpty { KTemporalPattern.parseTimePattern(timeString) })
    )

    constructor(time: Long, zone: KZone) : this(KTimeImpl(time, zone))

    companion object : KTemporalBuilder<KTime> by KTimeBuilderImpl

    /**
     * @see kTimeCustomPattern
     */
    override fun toString(): String = format(kTimeCustomPattern)
}