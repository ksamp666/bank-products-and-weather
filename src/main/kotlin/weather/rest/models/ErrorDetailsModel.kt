package weather.rest.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorDetailsModel {
    var code: Int? = null
    var type: String? = null
    var info: String? = null
}