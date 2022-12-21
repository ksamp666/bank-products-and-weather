package weather.rest.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import weather.rest.serializers.IsDayDeserializer
import weather.rest.serializers.IsDaySerializer
import java.time.LocalTime

@JsonIgnoreProperties(ignoreUnknown = true)
class CurrentWeatherModel {
    @JsonAlias("observation_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm a")
    var observationTime: LocalTime? = null
    var temperature: Byte? = null

    @JsonAlias("weather_code")
    var weatherCode: Int? = null

    @JsonAlias("weather_icons")
    var weatherIcons: List<String>? = null

    @JsonAlias("weather_descriptions")
    var weatherDescriptions: List<String>? = null

    @JsonAlias("wind_speed")
    var windSpeed: Int? = null

    @JsonAlias("wind_degree")
    var windDegree: Int? = null

    @JsonAlias("wind_dir")
    var windDir: String? = null
    var pressure: Int? = null
    var precip: Float? = null
    var humidity: Int? = null
    var cloudcover: Int? = null
    var feelslike: Byte? = null

    @JsonAlias("uv_index")
    var uvIndex: Byte? = null
    var visibility: Byte? = null

    @JsonAlias("is_day")
    @JsonDeserialize(using = IsDayDeserializer::class)
    @JsonSerialize(using = IsDaySerializer::class)
    var day: Boolean? = null
}