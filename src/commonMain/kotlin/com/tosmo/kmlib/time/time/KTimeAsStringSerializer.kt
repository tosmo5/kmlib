package com.tosmo.kmlib.time.time

import com.tosmo.kmlib.time.kTimeCustomPattern
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object KTimeAsStringSerializer : KSerializer<KTime> {

    internal val pattern: String
        get() = kTimeCustomPattern

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("KTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: KTime) {
        encoder.encodeString(value.format(pattern))
    }

    override fun deserialize(decoder: Decoder): KTime {
        return KTime(decoder.decodeString(), pattern)
    }
}