package com.tosmo.kmlib.time.datetime

import com.tosmo.kmlib.time.KTemporalBuilder
import java.time.LocalDateTime

internal actual object KDateTimeBuilderImpl : KTemporalBuilder<KDateTime> {
    override fun now(): KDateTime {
        return KDateTime(KDateTimeImpl(LocalDateTime.now()))
    }
}