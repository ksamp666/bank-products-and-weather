package weather.rest.requests

import io.qameta.allure.Step
import io.restassured.response.ValidatableResponse

class GetHistoricalWeatherRequest : WeatherRequestBase<GetHistoricalWeatherRequest>() {
    companion object {
        private const val REQUEST_URL = "historical"
    }

    @Step("Send historical weather request for city")
    override fun sendRequest(): ValidatableResponse {
        return getDefaultRequestSpecification()
            .get(url(REQUEST_URL))
            .then()
    }
}