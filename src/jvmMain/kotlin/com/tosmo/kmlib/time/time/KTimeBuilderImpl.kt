package com.tosmo.kmlib.time.time

import com.tosmo.kmlib.time.KTemporalBuilder
import java.time.LocalTime

internal actual object KTimeBuilderImpl : KTemporalBuilder<KTime> {
    override fun now(): KTime {
        return KTime(KTimeImpl(LocalTime.now()))
    }
}