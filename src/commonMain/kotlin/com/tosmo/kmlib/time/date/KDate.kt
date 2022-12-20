package com.tosmo.kmlib.time.date

import com.tosmo.kmlib.time.*
import com.tosmo.kmlib.time.zone.KZone
import kotlinx.serialization.Serializable

/**
 *
 * @author Thomas Miao
 */
@Serializable(KDateAsStringSerializer::class)
open class KDate internal constructor(internal val impl: KDateImpl) : KDateBase by impl {

    constructor(dateString: String, pattern: String = "") : this(
        KDateImpl(dateString, pattern.ifEmpty { KTemporalPattern.parseDatePattern(dateString) })
    )

    constructor(date: Long, zone: KZone) : this(KDateImpl(date, zone))

    companion object : KTemporalBuilder<KDate> by KDateBuilderImpl

    /**
     * yyyy-MM-dd
     */
    override fun toString(): String = format(KTemporalPattern.DEFAULT_DATE_PATTERN)
}