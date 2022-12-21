package weather.rest.requests

import io.restassured.response.ValidatableResponse

interface IRequest {
    fun sendRequest(): ValidatableResponse
    fun addRequestParam(key: String, value: String): IRequest
    fun addRequestParams(paramsToAdd: Map<String, String>): IRequest
}