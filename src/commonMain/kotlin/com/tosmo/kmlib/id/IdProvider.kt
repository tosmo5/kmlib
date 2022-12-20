package com.tosmo.kmlib.id

/**
 * ID分配器
 *
 * @author Thomas Miao
 */
interface IdProvider<T> {
    /**
     * 生成一个唯一的ID
     *
     * 在实现此方法时应注意数据的同步
     */
    suspend fun provide(): T
}