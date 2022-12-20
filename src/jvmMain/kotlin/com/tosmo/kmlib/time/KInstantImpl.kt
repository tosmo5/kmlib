package com.tosmo.kmlib.time

internal actual object KInstantImpl : InstantInterface {

    override val currentMilli: Long
        get() = System.currentTimeMillis()
}