package com.tosmo.kmlib.time

object KTemporalPattern {

    /**
     * 2022-01-23
     */
    const val DATE = "yyyy-MM-dd"

    /**
     * 2022/01/23
     */
    const val DATE_SLASH = "yyyy/MM/dd"

    /**
     * 20220123
     */
    const val DATE_SHORT = "yyyyMMdd"

    /**
     * 01-23
     */
    const val DATE_NO_YEAR = "MM-dd"

    /**
     * 01/23
     */
    const val DATE_NO_YEAR_SLASH = "MM/dd"

    /**
     * 2022-01
     */
    const val DATE_NO_DAY = "yyyy-MM"

    /**
     * 2022/01
     */
    const val DATE_NO_DAY_SLASH = "yyyy/MM"

    /**
     * 20220123182055
     */
    const val DATE_TIME_SHORT = "yyyyMMddHHmmss"

    /**
     * 2022-01-23 18:20
     */
    const val DATE_TIME_NO_SECOND = "yyyy-MM-dd HH:mm"

    /**
     * 2022/01/23 18:20
     */
    const val DATE_TIME_NO_SECOND_DATE_SLASH = "yyyy/MM/dd HH:mm"

    /**
     * 20220123 18:20:55
     */
    const val DATE_TIME_DATE_SHORT = "yyyyMMdd HH:mm:ss"

    /**
     * 2022-01-23 18:20:55
     */
    const val DATE_TIME = "yyyy-MM-dd HH:mm:ss"

    /**
     * 2022/01/23 18:20:55
     */
    const val DATE_TIME_DATE_SLASH = "yyyy/MM/dd HH:mm:ss"

    /**
     * 18:20:55
     */
    const val TIME = "HH:mm:ss"

    /**
     * 182055
     */
    const val TIME_SHORT = "HHmmss"

    /**
     * 18:20
     */
    const val TIME_NO_SECOND = "HH:mm"

    /**
     * 1820
     */
    const val TIME_SHORT_NO_SECONDE = "HHmm"

    private const val MINUS = "-"

    private const val SLASH = "/"

    const val DEFAULT_DATE_PATTERN = DATE

    const val DEFAULT_DATE_TIME_PATTERN = DATE_TIME

    const val DEFAULT_TIME_PATTERN = TIME

    /**
     * 解析[dateTimeStr]的格式，返回[KTemporalPattern]中已有"DATE_TIME"格式的一种
     */
    fun parseDateTimePattern(dateTimeStr: String): String {
        return when (dateTimeStr.length) {
            19 -> when (MINUS in dateTimeStr) {
                true -> DATE_TIME // yyyy-MM-dd HH:mm:ss
                false -> DATE_TIME_DATE_SLASH // yyyy/MM/dd HH:mm:ss
            }

            16 -> when (MINUS in dateTimeStr) {
                true -> DATE_TIME_NO_SECOND  // yyyy-MM-dd HH:mm
                false -> DATE_TIME_NO_SECOND_DATE_SLASH  // yyyy/MM/dd HH:mm
            }

            17 -> DATE_TIME_DATE_SHORT  // yyyyMMdd HH:mm:ss
            14 -> DATE_TIME_SHORT // yyyyMMddHHmmss
            else -> throw IllegalArgumentException("找不到对应的日期格式，$dateTimeStr")
        }
    }

    /**
     * 解析日期的格式
     */
    fun parseDatePattern(dateStr: String): String {
        return when (dateStr.length) {
            10 -> when (MINUS in dateStr) {
                true -> DATE // yyyy-MM-dd
                false -> DATE_SLASH // yyyy/MM/dd
            }

            8 -> DATE_SHORT // yyyyMMdd
            else -> parseDateTimePattern(dateStr)
        }
    }

    /**
     * 解析日期的格式
     */
    fun parseTimePattern(timeStr: String): String {
        return when (timeStr.length) {
            8 -> TIME // HH:mm:ss
            6 -> TIME_SHORT // HHmmss
            else -> parseDateTimePattern(timeStr)
        }
    }

    /**
     * 解析[dateTimeStr]的格式，返回[KTemporalPattern]中已有"DATE_TIME"格式的一种或空
     */
    fun parseDatePatternOrNull(dateTimeStr: String): String? {
        return runCatching { parseDateTimePattern(dateTimeStr) }.getOrNull()
    }
}