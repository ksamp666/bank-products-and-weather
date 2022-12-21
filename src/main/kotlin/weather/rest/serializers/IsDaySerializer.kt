package weather.rest.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider


class IsDaySerializer : JsonSerializer<Boolean>() {
    override fun serialize(value: Boolean, generator: JsonGenerator, provider: SerializerProvider) {
        val result = if (value) "yes" else "no"
        generator.writeString(result)
    }
}