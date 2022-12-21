package weather.rest.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorModel {
    var success: Boolean? = null
    var error: ErrorDetailsModel? = null
}