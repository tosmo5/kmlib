package com.tosmo.kmlib.typemap.exception

import com.tosmo.kmlib.typemap.entity.TypeKey


/**
 *
 * @author Thomas Miao
 */
class MissingKeyException(message: String? = null) : Exception("找不到键${message?.let { ":${message}" } ?: ""}") {

    constructor(key: TypeKey) : this("[$key.keyName]")
}