package com.tosmo.kmlib.provider

import com.tosmo.kmlib.reflect.Reflecter
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation

/**
 * 提供者
 *
 * @author Thomas Miao
 */
@Suppress("UNCHECKED_CAST")
object Provider {

    private val mRepoMap: MutableMap<KClass<*>, Any> = mutableMapOf()

    /**
     * 根据[kClass]提供对应的对象
     */
    operator fun <T : Any> get(kClass: KClass<T>): T {
        return mRepoMap.getOrPut(kClass) {
            providerFactory(kClass).create(kClass)
        } as T
    }

    /**
     * 根据[kClass]提供对应的对象，在必要时使用[args]进行创建
     */
    operator fun <T : Any> get(kClass: KClass<T>, args: Map<String, *>): T {
        return mRepoMap.getOrPut(kClass) {
            providerFactory(kClass).create(kClass, args)
        } as T
    }

    private fun providerFactory(kClass: KClass<*>): Factory<*> {
        return kClass.findAnnotation<Provision>()?.factory?.let {
            it.objectInstance ?: it.createInstance()
        } ?: DefaultFactory
    }

    /**
     * 工厂，必须要有无参构造
     */
    interface Factory<out T : Any> {

        fun create(kClass: KClass<*>): T {
            return Reflecter.createBean(kClass) as T
        }

        fun create(kClass: KClass<*>, args: Map<String, *>): T {
            return Reflecter.createBean(kClass, args) as T
        }
    }

    internal object DefaultFactory : Factory<Any>
}