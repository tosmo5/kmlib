package com.tosmo.kmlib.calc

/**
 * 分段计算器
 *
 * @author Thomas Miao
 */
class SegmentationCalculator {

    /**
     * 每段
     */
    data class Section(
        val start: Double,
        val end: Double,
        val percentage: Double
    )

    private val mSections = mutableListOf<Section>()

    /**
     * 分段信息
     */
    val setions
        get() = mSections.toList()

    /**
     * 添加
     */
    fun add(sectionEnd: Double, percentage: Double) {
        val lastRange = mSections.lastOrNull()
        val rangeStart = lastRange?.end ?: 0.0
        require(sectionEnd > rangeStart)
        require(percentage > 0)
        mSections.add(Section(rangeStart, sectionEnd, percentage))
    }

    /**
     * 批量添加
     */
    fun addAll(vararg pair: Pair<Double, Double>) {
        pair.forEach {
            add(it.first, it.second)
        }
    }

    /**
     * 计算
     */
    fun calc(number: Double): Double {
        return mSections.sumOf {
            if (number >= it.end) {
                (it.end - it.start) * it.percentage / 100.0
            } else if (number >= it.start) {
                number - it.start * it.percentage / 100.0
            } else 0.0
        }
    }

    fun clear() {
        mSections.clear()
    }
}
