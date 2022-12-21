package weather.utils

//import org.junit.jupiter.api.Assertions
import org.junit.Assert
import java.lang.StringBuilder

class ObjectsComparator {
    companion object {
        fun compareMaps(expected: Map<String, Any?>, actual: Map<String, Any?>) {
            val errors = StringBuilder()
            expected.forEach { (key, value) ->
                if (value != null) {
                    if (actual[key] is Collection<*>) {
                        val expectedCollection = value.toString().split(",")
                        val actualCollection = actual[key] as Collection<*>
                        if (expectedCollection.size != actualCollection.size
                            || !expectedCollection.containsAll(actualCollection)
                            || !actualCollection.containsAll(expectedCollection)
                        ) {
                            errors.appendLine("Actual list $actualCollection is not the same as expected $expectedCollection in field '$key'")
                        }
                    } else {
                        if (value != actual[key].toString()) {
                            errors.appendLine("Item '${actual[key]}' is not the same as expected '$value' in field '$key'")
                        }
                    }
                }
            }

            Assert.assertTrue(
                "Expected object $expected does not match actual $actual with following errors:${System.lineSeparator()}$errors",
                errors.isEmpty(),
            )
        }
    }
}