package com.tosmo.kmlib.time.date

import com.tosmo.kmlib.time.JvmTimeUtils
import com.tosmo.kmlib.time.KTemporalPattern
import java.util.*

fun KDate.Companion.from(date: Date): KDate {
    return KTemporalPattern.DEFAULT_DATE_PATTERN.let { KDate(JvmTimeUtils.format(date, it), it) }
}