package weather.rest.serializers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class IsDayDeserializer : JsonDeserializer<Boolean>() {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): Boolean {
        return when (val value = parser.valueAsString) {
            "yes" -> true
            "no" -> false
            else -> value.toBoolean()
        }
    }
}