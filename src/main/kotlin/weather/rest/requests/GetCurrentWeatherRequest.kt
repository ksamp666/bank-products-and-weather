package weather.rest.requests

import io.qameta.allure.Step
import io.restassured.response.ValidatableResponse

class GetCurrentWeatherRequest : WeatherRequestBase<GetCurrentWeatherRequest>() {
    companion object {
        private const val REQUEST_URL = "current"
    }

    @Step("Send current weather request for city")
    override fun sendRequest(): ValidatableResponse {
        return getDefaultRequestSpecification()
            .get(url(REQUEST_URL))
            .then()
    }
}