package com.tosmo.kmlib.typemap.repo

import com.tosmo.kmlib.typemap.entity.TypeKey


/**
 * 键的存储接口
 *
 * @author Thomas Miao
 */
interface TypeKeyRepository<K : TypeKey> {
    /**
     * 添加[key]
     */
    fun addKey(key: K): Boolean

    /**
     * 添加[keys]中所有的[TypeKey]，返回成功的数量
     */
    fun addAllKeys(keys: Collection<K>): Int

    /**
     * 删除[key]，返回删除结果
     */
    fun deleteKey(key: K): Boolean

    /**
     * 删除所有的[TypeKey]，返回成功的数量
     */
    fun deleteAllKeys(): Int

    /**
     * 取得键名为[keyValue]的[TypeKey]
     */
    fun getKeyByKeyValue(keyValue: String): K?

    /**
     * 根据[keyValues]集合，取得对应的所有[TypeKey]
     */
    fun getKeysByKeyValues(keyValues: Collection<String>): List<K>

    /**
     * 取得保存的所有[TypeKey]
     */
    fun getAllKeys(): List<K>

    /**
     * 取得保存的所有[TypeKey]的名称
     */
    fun getAllKeyValues(): List<String>

    /**
     * 查询是否没有[TypeKey]
     */
    fun isNoKey(): Boolean

    /**
     * 查询是否存在名为[keyValue]的[TypeKey]
     */
    fun hasKeyValue(keyValue: String): Boolean

    /**
     * 查询是否存在[key]
     */
    fun hasKey(key: K): Boolean

    /**
     * 计算[TypeKey]的数量
     */
    fun countKey(): Int
}