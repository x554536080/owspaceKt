package com.kuma.owspacekt.model.util

import com.google.gson.*
import org.joda.time.DateTime
import java.lang.reflect.Type

class DateTimeAdapter : JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
    override fun serialize(
        src: DateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): DateTime {
        return DateTime(json?.asString)
    }
}