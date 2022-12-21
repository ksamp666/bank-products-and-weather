package weather.glue.steps

import io.cucumber.java.Before
import io.cucumber.java.Scenario
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.hamcrest.CoreMatchers.`is`
import weather.glue.dataholders.ApiResponsesHolder
import weather.rest.requests.GetCurrentWeatherRequest
import weather.rest.requests.GetHistoricalWeatherRequest
import weather.rest.requests.InvalidApiFunctionRequest
import weather.rest.utils.ModelsUtils
import weather.utils.ObjectsComparator

class WeatherApiSteps : BaseSteps() {

    @Before
    fun setUp(scenario: Scenario) {
        this.scenario = scenario
    }

    enum class CurrentWeatherResponseDataType {
        REQUEST, CURRENT, LOCATION
    }

    enum class WeatherType {
        CURRENT, HISTORICAL
    }

    @When("^We get (CURRENT|HISTORICAL) weather for (.+) query via rest API$")
    fun getCurrentWeather(weatherType: WeatherType, query: String) {
        getCurrentWeather(weatherType, query, emptyMap())
    }

    @When("^We get (CURRENT|HISTORICAL) weather for (.+) query via rest API with additional params:$")
    fun getCurrentWeather(weatherType: WeatherType, query: String, paramsToAdd: Map<String, String>) {
        val request = when(weatherType) {
            WeatherType.CURRENT -> GetCurrentWeatherRequest()
            WeatherType.HISTORICAL -> GetHistoricalWeatherRequest()
        }
        val response = request
            .setQuery(query)
            .addRequestParams(paramsToAdd)
            .sendRequest()

        ApiResponsesHolder.put(getScenarioId(), response)
    }

    @Then("^We check that API request is completed with status (\\d+)$")
    fun checkApiRequestStatus(statusCode: Int) {
        ApiResponsesHolder.get(getScenarioId())
            .statusCode(statusCode)
    }

    @Then("^We check that (REQUEST|CURRENT|LOCATION) data in completed weather request is correct:$")
    fun checkCurrentWeatherResponseData(dataType: CurrentWeatherResponseDataType, expectedWeatherMap: Map<String, String>) {
        val weatherResponse = ApiResponsesHolder
            .getAsCurrentWeatherResponse(getScenarioId())
        val actualObj: Any? = when(dataType) {
            CurrentWeatherResponseDataType.REQUEST -> weatherResponse.request
            CurrentWeatherResponseDataType.CURRENT -> weatherResponse.current
            CurrentWeatherResponseDataType.LOCATION -> weatherResponse.location
        }
        val actualWeatherMap = ModelsUtils.toMap(actualObj)
        ObjectsComparator.compareMaps(expectedWeatherMap, actualWeatherMap)
    }

    @When("^We request invalid API function$")
    fun requestInvalidApiFunction() {
        ApiResponsesHolder.put(getScenarioId(), InvalidApiFunctionRequest().sendRequest())
    }

    @When("^We get current weather with invalid API key$")
    fun requestWithInvalidApiKey() {
        val response = GetCurrentWeatherRequest()
            .useInvalidAuthKey()
            .setQuery("Moscow")
            .sendRequest()

        ApiResponsesHolder.put(getScenarioId(), response)
    }

    @Then("^We check that API request is completed with error:$")
    fun checkApiRequestIsCompletedWithError(expectedErrorDetailsMap: Map<String, String>) {
        ApiResponsesHolder.get(getScenarioId()).assertThat().body("success", `is`(false))
        val errorDetails = ApiResponsesHolder.getAsErrorResponse(getScenarioId()).error
        val actualErrorDetailsMap = ModelsUtils.toMap(errorDetails)
        ObjectsComparator.compareMaps(expectedErrorDetailsMap, actualErrorDetailsMap)
    }
}