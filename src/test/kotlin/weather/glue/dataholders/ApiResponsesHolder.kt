package weather.glue.dataholders

import io.restassured.response.ValidatableResponse
import weather.rest.models.CurrentWeatherResponseModel
import weather.rest.models.ErrorModel
import java.lang.RuntimeException
import java.util.concurrent.ConcurrentHashMap

class ApiResponsesHolder {
    companion object {
        private var holder = ConcurrentHashMap<String, ValidatableResponse>()

        fun get(scenarioId: String): ValidatableResponse {
            return holder[scenarioId] ?: throw RuntimeException("No data found in holder for scenario $scenarioId")
        }

        fun getAsCurrentWeatherResponse(scenarioId: String): CurrentWeatherResponseModel {
            return get(scenarioId)
                .extract()
                .`as`(CurrentWeatherResponseModel::class.java)
        }

        fun getAsErrorResponse(scenarioId: String): ErrorModel {
            return get(scenarioId)
                .extract()
                .`as`(ErrorModel::class.java)
        }

        fun put(scenarioId: String, response: ValidatableResponse) {
            holder[scenarioId] = response
        }
    }
}