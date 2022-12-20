package com.tosmo.kmlib.provider

import kotlin.reflect.KClass

/**
 * 提供对象的信息，可以不用
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Provision(
    val factory: KClass<out Provider.Factory<Any>> = Provider.Factory::class
)
