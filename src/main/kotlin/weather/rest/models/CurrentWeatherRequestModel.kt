package weather.rest.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CurrentWeatherRequestModel {
    var type: String? = null
    var query: String? = null
    var language: String? = null
    var unit: String? = null
}