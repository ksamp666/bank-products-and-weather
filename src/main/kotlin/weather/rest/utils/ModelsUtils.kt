package weather.rest.utils

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

class ModelsUtils {
    companion object {
        private val om = ObjectMapper()

        init {
            om.registerModule(JavaTimeModule())
        }

        fun toMap(obj: Any?): Map<String, Any?> {
            return om.convertValue(obj, object : TypeReference<Map<String, Any?>>() {})
        }
    }
}