package weather.rest.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@JsonIgnoreProperties(ignoreUnknown = true)
class CurrentWeatherResponseModel {
    var request: CurrentWeatherRequestModel? = null
    var location: CurrentWeatherLocationModel? = null
    var current: CurrentWeatherModel? = null
}