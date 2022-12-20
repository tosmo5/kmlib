package com.tosmo.kmlib.time.date

import com.tosmo.kmlib.time.KTemporalBuilder
import java.time.LocalDate

/**
 *
 * @author Thomas Miao
 */
internal actual object KDateBuilderImpl : KTemporalBuilder<KDate> {
    override fun now(): KDate {
        return KDate(KDateImpl(LocalDate.now()))
    }
}