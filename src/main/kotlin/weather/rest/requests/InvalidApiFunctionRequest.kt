package weather.rest.requests

import io.qameta.allure.Step
import io.restassured.response.ValidatableResponse

class InvalidApiFunctionRequest : WeatherRequestBase<InvalidApiFunctionRequest>() {
    companion object {
        private const val REQUEST_URL = "invalid-api-function"
    }

    @Step("Send invalid API function request")
    override fun sendRequest(): ValidatableResponse {
        return getDefaultRequestSpecification()
            .get(url(REQUEST_URL))
            .then()
    }
}