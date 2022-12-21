package weather.rest.requests

import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured.given
import io.restassured.specification.RequestSpecification
import weather.utils.PropsReader


abstract class WeatherRequestBase<RequestType : IRequest> : IRequest {
    companion object {
        private const val INVALID_API_KEY = "INVALID_API_KEY"
        private const val ACCESS_KEY_PARAM = "access_key"
        private const val QUERY_PARAM = "query"


        private val BASE_URL = PropsReader.getApiUrl()
        private val API_KEY = PropsReader.getApiKey()
    }

    private val params = HashMap<String, String>()
    private var authKey = API_KEY
    private var body: Any? = null

    protected fun url(path: String): String {
        return BASE_URL + path
    }

    protected fun getDefaultRequestSpecification(): RequestSpecification {
        params[ACCESS_KEY_PARAM] = authKey

        return given()
            .filter(AllureRestAssured())
            .params(params)
    }

    fun setQuery(query: String): RequestType {
        addRequestParam(QUERY_PARAM, query)
        return this as RequestType
    }

    override fun addRequestParam(key: String, value: String): RequestType {
        params[key] = value
        return this as RequestType
    }

    override fun addRequestParams(paramsToAdd: Map<String, String>): RequestType {
        paramsToAdd.forEach { params[it.key] = it.value }
        return this as RequestType
    }

    fun useDefaultAuthKey(): RequestType {
        authKey = API_KEY
        return this as RequestType
    }

    fun useInvalidAuthKey(): RequestType {
        authKey = INVALID_API_KEY
        return this as RequestType
    }
}