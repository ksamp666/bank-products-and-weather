# Sber Test Task

## How to run

Using ``mvn clean test`` will run all available tests:
- Unit tests for bank products
- API tests for weather service

To simplify the usage, weather API key is stored directly in properties file.

## Reporting and results

Use ``mvn allure:serve`` to generate Allure report.

Allure is configured to store only API tests results.
