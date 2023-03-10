package weather.utils

import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

class PropsReader {

    companion object {
        private const val LOCAL_PROPS_FILE = "src/main/resources/properties/weather.properties"
        private val properties: Properties = Properties()

        private fun loadProperties() {
            val inputStream: InputStream = FileInputStream(File(LOCAL_PROPS_FILE))
            properties.load(inputStream)
        }

        private fun getProperty(propName: String): String {
            if (properties.isEmpty) {
                loadProperties()
            }
            return properties.getProperty(propName)
        }

        fun getApiUrl(): String {
            return getProperty("api.url")
        }

        fun getApiKey(): String {
            return getProperty("api.key")
        }
    }
}