package com.tosmo.kmlib.time.datetime

import com.tosmo.kmlib.time.kDateTimeCustomPattern
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object KDateTimeAsStringSerializer : KSerializer<KDateTime> {

    internal val pattern: String
        get() = kDateTimeCustomPattern

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("KDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): KDateTime {
        return KDateTime(decoder.decodeString(), pattern)
    }

    override fun serialize(encoder: Encoder, value: KDateTime) {
        encoder.encodeString(value.format(pattern))
    }
}