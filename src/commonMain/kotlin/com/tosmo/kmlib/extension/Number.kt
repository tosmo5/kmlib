package com.tosmo.kmlib.extension

/**
 * 将十进制的数以[carryBit]的进制转换，返回转换后每一位所对应的十进制值
 *
 * <p>
 *
 *     2.decTo(2) => [1, 0]
 *     4.decTo(2) => [1, 0, 0]
 *
 *     12.decTo(5) => [2, 2]
 *     12.decTo(6) => [2, 0]
 */
fun Long.decTo(carryBit: Int): LongArray {
    var n = this
    val arr = ArrayDeque<Long>()
    do {
        arr.addFirst(n % carryBit)
        n /= carryBit
        if (n in 1 until carryBit) {
            arr.addFirst(n)
        }
    } while (n >= carryBit)
    return arr.toLongArray()
}

/**
 * @see Long.decTo
 */
fun Int.decTo(carryBit: Int): IntArray {
    val arr = toLong().decTo(carryBit)
    return IntArray(arr.size) {
        arr[it].toInt()
    }
}

/**
 * @see Long.decTo
 */
fun Short.decTo(carryBit: Int): ShortArray {
    val arr = toLong().decTo(carryBit)
    return ShortArray(arr.size) {
        arr[it].toShort()
    }
}
