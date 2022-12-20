package com.tosmo.kmlib.typemap

import com.tosmo.kmlib.typemap.entity.Index
import com.tosmo.kmlib.typemap.entity.TypeKey
import com.tosmo.kmlib.typemap.entity.TypeValue
import com.tosmo.kmlib.typemap.exception.MissingKeyException
import com.tosmo.kmlib.typemap.exception.ValueTypeException
import com.tosmo.kmlib.typemap.privider.ValueRepositoryProvider
import com.tosmo.kmlib.typemap.repo.IndexRepository
import com.tosmo.kmlib.typemap.repo.TypeKeyRepository
import kotlin.reflect.full.isSuperclassOf

/**
 *
 * @author Thomas Miao
 */
internal class TypeMapImpl<K : TypeKey, N : Index>(
    keyRepo: TypeKeyRepository<K>,
    nodeRepo: IndexRepository<K, N>,
    valueRepoProvider: ValueRepositoryProvider<K, N>
) : TypeMap<K, N> {

    override val keyRepository: TypeKeyRepository<K> = keyRepo

    override val indexRepository: IndexRepository<K, N> = nodeRepo

    override val valueRepositoryProvider: ValueRepositoryProvider<K, N> = valueRepoProvider

    override val keysSize: Int
        get() = keyRepository.countKey()

    override val keys: List<K>
        get() = keyRepository.getAllKeys()

    override val keyNames: List<String>
        get() = keyRepository.getAllKeyValues()

    override fun containsKey(key: K): Boolean {
        return keyRepository.hasKey(key)
    }

    override fun containsKey(keyValue: String): Boolean {
        return keyRepository.hasKeyValue(keyValue)
    }

    override fun containsValue(keyValue: String, value: TypeValue<*, N>): Boolean {
        return getKey(keyValue)?.let {
            containsValue(it, value)
        } ?: false
    }

    override fun containsValue(key: K, value: TypeValue<*, N>): Boolean {
        return valueRepositoryProvider.provide(key).containsValue(value)
    }

    override fun clearValues(): Int {
        indexRepository.deleteAllIndex()
        return keys.sumOf {
            valueRepositoryProvider.provide(it).deleteByKey(it)
        }
    }

    override fun clearKeyValues(keyValue: String): Int {
        return getKey(keyValue)?.let { clearKeyValues(it) } ?: 0
    }

    override fun clearKeyValues(key: K): Int {
        // 根据键除节点
        indexRepository.deleteByKey(key)
        // 根据键分配值的存储接口并删除键的值，返回删除的个数
        return valueRepositoryProvider.provide(key).deleteByKey(key)
    }

    override fun clearKey(keyValue: String, clearValues: Boolean) {
        getKey(keyValue)?.let {// 取得键
            clearKey(it, clearValues)
        }
    }

    override fun clearKey(key: K, clearValues: Boolean) {
        // 根据键除节点
        indexRepository.deleteByKey(key)
        if (clearValues) {
            // 根据键分配值的存储接口并删除键的值，返回删除的个数
            valueRepositoryProvider.provide(key).deleteByKey(key)
        }
        // 删除键
        keyRepository.deleteKey(key)
    }

    override fun clearAll(): Int {
        clearValues()
        return keyRepository.deleteAllKeys()
    }

    override fun isKeysEmpty(): Boolean {
        return keyRepository.isNoKey()
    }

    override fun putKey(key: K): Boolean {
        return keyRepository.addKey(key)
    }

    override fun putMultiKeys(keys: Collection<K>): Int {
        return keyRepository.addAllKeys(this.keys)
    }

    override fun getKey(keyValue: String): K? {
        return keyRepository.getKeyByKeyValue(keyValue)
    }

    override fun getKey(node: N): K? {
        return indexRepository.getKeyByIndex(node)
    }

    override fun getMutliKeys(keyValues: Collection<String>): List<K> {
        return keyRepository.getKeysByKeyValues(keyValues)
    }

    override fun getValue(index: N): TypeValue<*, N>? {
        return getKey(index)?.let { getValue(it, index) }
    }

    override fun getValue(key: K, index: N): TypeValue<*, N>? {
        return valueRepositoryProvider.provide(key).getValue(index)
    }

    override fun setValue(index: N, value: TypeValue<*, N>): Boolean {
        return getKey(index)?.let {
            setValue(it, index, value)
        } ?: throw MissingKeyException()
    }

    override fun setValue(key: K, index: N, value: TypeValue<*, N>): Boolean {
        // 判断值的类型是否正确
        if (!checkValueType(key, value)) {
            throw ValueTypeException(key, value)
        }
        val valueRepo = valueRepositoryProvider.provide(key)
        // 更新或新增
        return if (containsValue(key, value)) {
            valueRepo.updateValue(value)
        } else {
            valueRepo.addValue(value)
        } && indexRepository.addIndex(index)
    }

    override fun getDefaultIndex(key: K): N? {
        return indexRepository.getDefaultIndex(key)
    }

    override fun getDefaultIndex(keyValue: String): N? {
        return getKey(keyValue)?.let { getDefaultIndex(it) }
    }

    override fun getDefaultValue(keyValue: String): TypeValue<*, N>? {
        return getKey(keyValue)?.let { getDefaultValue(it) }
    }

    override fun getDefaultValue(key: K): TypeValue<*, N>? {
        return indexRepository.getDefaultIndex(key)?.let {// 取得默认节点
            valueRepositoryProvider.provide(key).getValue(it)
        }
    }

    override fun setDefaultValue(keyValue: String, value: TypeValue<*, N>): Boolean {
        // 取出值对象
        return getKey(keyValue)?.let { key ->
            setDefaultValue(key, value)
        } ?: throw MissingKeyException("[${keyValue}]")
    }

    override fun setDefaultValue(key: K, value: TypeValue<*, N>): Boolean {
        // 判断值的类型是否正确
        if (!checkValueType(key, value)) {
            throw ValueTypeException(key, value)
        }
        // 分配值存储接口
        val valueRepo = valueRepositoryProvider.provide(key)
        // 如果存在则更新，否则新增
        return if (valueRepo.containsValue(value)) {
            valueRepo.updateValue(value)
        } else {
            valueRepo.addValue(value)
        }
    }

    internal fun checkValueType(key: TypeKey, value: TypeValue<*, *>): Boolean {
        return value.valueValue?.let {
            key.typeKClass.isSuperclassOf(it::class)
        } ?: true
    }
}