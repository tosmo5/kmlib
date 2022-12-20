package com.tosmo.kmlib.time


/**
 *
 * @author Thomas Miao
 */
object KTimeUtils : InstantInterface by KInstantImpl {

    /**
     * 返回[action]运行的毫秒数
     */
    fun onMilli(action: () -> Unit): Long {
        return currentMilli.let {
            action()
            currentMilli - it
        }
    }

    /**
     * 返回[action]运行的秒数
     */
    fun onSec(action: () -> Unit): Float {
        return onMilli(action) / 1000f
    }
}
