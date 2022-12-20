package com.tosmo.kmlib.typemap.entity

/**
 * 值
 *
 * @author Thomas Miao
 */
interface TypeValue<T, I : Index> {
    /**
     * 值的值
     */
    val valueValue: T
}