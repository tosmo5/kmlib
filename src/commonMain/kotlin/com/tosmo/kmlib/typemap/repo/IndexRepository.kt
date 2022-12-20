package com.tosmo.kmlib.typemap.repo

import com.tosmo.kmlib.typemap.entity.Index
import com.tosmo.kmlib.typemap.entity.TypeKey


/**
 * [Index]存储接口
 *
 * @author Thomas Miao
 */
interface IndexRepository<K : TypeKey, I : Index> {

    /**
     * 添加[index]，不需要的话返回true
     */
    fun addIndex(index: I): Boolean = true

    /**
     * 删除所有[Index]，不需要的话返回true
     */
    fun deleteAllIndex(): Boolean = true

    /**
     * 删除[key]下的所有[Index]，不需要直接返回0
     */
    fun deleteByKey(key: K): Int = 0

    /**
     * 根据[key]取得默认[Index]
     */
    fun getDefaultIndex(key: K): I?

    /**
     * 取得[index]对应的[TypeKey]
     */
    fun getKeyByIndex(index: I): K?
}