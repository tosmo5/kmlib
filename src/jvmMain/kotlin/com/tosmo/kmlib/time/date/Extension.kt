package com.tosmo.kmlib.time.date

import com.tosmo.kmlib.time.JTimeUtils
import com.tosmo.kmlib.time.KTemporalPattern
import java.util.*

fun KDate.Companion.from(date: Date): KDate {
    return KTemporalPattern.DEFAULT_DATE_PATTERN.let { KDate(JTimeUtils.format(date, it), it) }
}