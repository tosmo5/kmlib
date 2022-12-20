package com.tosmo.kmlib.id

import com.tosmo.kmlib.time.KTemporalPattern
import com.tosmo.kmlib.time.KTimeUtils
import com.tosmo.kmlib.time.datetime.KDateTime
import com.tosmo.kmlib.time.zone.KZone

/**
 * 以标准的雪花算法实现的ID分配器
 *
 * @property timeEpoch 时间基础参数
 * @property providerId 分配器编号
 * @property datacenterId 数据中心编号
 */
class SnowflakeIdProvider internal constructor(val timeEpoch: Long, val datacenterId: Long, val providerId: Long) :
    IdProvider<Long> {

    class Builder {
        internal var dateTimeString = "2022-08-31 00:00:00"
        internal var pattern: String = KTemporalPattern.DEFAULT_DATE_TIME_PATTERN
        internal var zone = KZone.systemDefault

        internal var isDateTimeChanged = false

        fun dateTime(dateTimeString: String): Builder {
            this.dateTimeString = dateTimeString
            isDateTimeChanged = true
            return this
        }

        fun pattern(pattern: String): Builder {
            this.pattern = pattern
            isDateTimeChanged = false
            return this
        }

        fun zone(zone: KZone): Builder {
            this.zone = zone
            return this
        }

        fun build(datacenterId: Long = 0, providerId: Long = 0): SnowflakeIdProvider {
            if (isDateTimeChanged) {
                pattern = KTemporalPattern.parseDateTimePattern(dateTimeString)
            }
            return SnowflakeIdProvider(KDateTime(dateTimeString, pattern).toEpochMilli(zone), datacenterId, providerId)
        }
    }

    private var mSequence: Long = 0

    //长度为5位
    private val mDatacenterIdBits = 5
    private val mProviderIdBits = 5

    //最大值
    private val mMaxProviderId = -1L xor (-1L shl mProviderIdBits)
    private val mMaxDatacenterId = -1L xor (-1L shl mDatacenterIdBits)

    //序列号id长度
    private val mSequenceBits = 12

    //序列号最大值
    private val mSequenceMask = -1L xor (-1L shl mSequenceBits)

    //工作id需要左移的位数，12位
    private val mProviderIdShift = mSequenceBits

    //数据id需要左移位数 12+5=17位
    private val mDatacenterIdShift = mSequenceBits + mProviderIdBits

    //时间戳需要左移位数 12+5+5=22位
    private val mTimestampLeftShift = mSequenceBits + mProviderIdBits + mDatacenterIdBits

    //上次时间戳，初始值为负数
    private var mLastTimestamp = -1L

    private val mTimeGen = { KTimeUtils.currentMilli }

    init {
        require(!(providerId > mMaxProviderId || providerId < 0)) {
            "worker Id can't be greater than $mMaxProviderId or less than 0"
        }
        require(!(datacenterId > mMaxDatacenterId || datacenterId < 0)) {
            "datacenter Id can't be greater than $mMaxDatacenterId or less than 0"
        }
    }

    /**
     * 下一个ID生成算法
     */
    @Synchronized
    override fun provide(): Long {
        var timestamp = mTimeGen()

        //获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
        require(timestamp >= mLastTimestamp) {
            "Clock moved backwards. Refusing to generate id for ${mLastTimestamp - timestamp} seconds"
        }

        //获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一；否则序列号赋值为0，从0开始。
        if (mLastTimestamp == timestamp) {
            mSequence = mSequence + 1 and mSequenceMask
            if (mSequence == 0L) {
                timestamp = tilNextMillis(mLastTimestamp)
            }
        } else {
            mSequence = 0
        }

        //将上次时间戳值刷新
        mLastTimestamp = timestamp

        return ((timestamp - timeEpoch) shl mTimestampLeftShift) or (datacenterId shl mDatacenterIdShift) or (providerId shl mProviderIdShift) or mSequence
    }

    /**
     * 获取时间戳，并与上次时间戳比较
     */
    private fun tilNextMillis(lastTimestamp: Long): Long =
        sequence { while (true) yield((mTimeGen())) }.first { it > lastTimestamp }
}
