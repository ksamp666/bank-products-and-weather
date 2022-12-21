package weather.glue.steps

import io.cucumber.java.Scenario
import io.qameta.allure.util.ResultsUtils.md5

open class BaseSteps {
    protected var scenario: Scenario? = null

    protected fun getScenarioId(): String {
        return md5(scenario!!.id)
    }
}