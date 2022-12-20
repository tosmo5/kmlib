package com.tosmo.kmlib.time.date

import com.tosmo.kmlib.time.kDateCustomPattern
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 *
 * @author Thomas Miao
 */
internal object KDateAsStringSerializer : KSerializer<KDate> {

    internal val pattern: String
        get() = kDateCustomPattern

    override fun deserialize(decoder: Decoder): KDate {
        return KDate(decoder.decodeString(), pattern)
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("KDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: KDate) {
        return encoder.encodeString(value.format(pattern))
    }
}
