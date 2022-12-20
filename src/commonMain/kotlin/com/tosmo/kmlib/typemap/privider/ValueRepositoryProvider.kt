package com.tosmo.kmlib.typemap.privider

import com.tosmo.kmlib.typemap.entity.Index
import com.tosmo.kmlib.typemap.entity.TypeKey
import com.tosmo.kmlib.typemap.entity.TypeValue
import com.tosmo.kmlib.typemap.repo.TypeValueRepository

/**
 * 值存储接口分配器
 *
 * @author Thomas Miao
 */
fun interface ValueRepositoryProvider<K : TypeKey, I : Index> {
    /**
     * @param key 键
     * @return 值的操作接口
     */
    fun provide(key: K): TypeValueRepository<K, I, TypeValue<*, I>>
}