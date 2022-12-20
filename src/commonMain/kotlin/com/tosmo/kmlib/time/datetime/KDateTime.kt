package com.tosmo.kmlib.time.datetime

import com.tosmo.kmlib.time.*
import com.tosmo.kmlib.time.zone.KZone
import kotlinx.serialization.Serializable

/**
 *
 * @author Thomas Miao
 */
@Serializable(KDateTimeAsStringSerializer::class)
open class KDateTime internal constructor(internal val impl: KDateTimeImpl) : KDateTimeBase by impl {

    constructor(dateTimeString: String, pattern: String = "") : this(
        KDateTimeImpl(dateTimeString, pattern.ifEmpty { KTemporalPattern.parseDateTimePattern(dateTimeString) })
    )

    constructor(dateTime: Long, zone: KZone) : this(KDateTimeImpl(dateTime, zone))

    companion object : KTemporalBuilder<KDateTime> by KDateTimeBuilderImpl

    /**
     * @see kDateTimeCustomPattern
     */
    override fun toString(): String = format(kDateTimeCustomPattern)
}