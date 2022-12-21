package weather.rest.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class CurrentWeatherLocationModel {
    var name: String? = null
    var country: String? = null
    var region: String? = null
    var lat: Double? = null
    var lon: Double? = null

    @JsonAlias("timezone_id")
    var timezoneId: String? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    var localtime: LocalDateTime? = null

    @JsonAlias("localtime_epoch")
    var localtimeEpoch: Long? = null

    @JsonAlias("utc_offset")
    var utcOffset: Float? = null
}