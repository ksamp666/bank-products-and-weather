Feature: Weather API tests

  Scenario Outline: Check current weather in <City> city via API
    When We get CURRENT weather for '<City>' query via rest API
    Then We check that API request is completed with status 200
    And We check that REQUEST data in completed weather request is correct:
      | type     | <RequestType>     |
      | query    | <RequestQuery>    |
      | language | <RequestLanguage> |
      | unit     | <RequestUnit>     |
    And We check that LOCATION data in completed weather request is correct:
      | name    | <LocationName>    |
      | country | <LocationCountry> |
      | region  | <LocationRegion>  |
    And We check that CURRENT data in completed weather request is correct:
      | windSpeed   | <CurrentWindSpeed>   |
      | windDir     | <CurrentWindDir>     |
      | temperature | <CurrentTemperature> |
      | humidity    | <CurrentHumidity>    |

    Examples:
      | City        | RequestType | RequestQuery        | RequestLanguage | RequestUnit | LocationName | LocationCountry | LocationRegion | CurrentWindSpeed | CurrentWindDir | CurrentTemperature | CurrentHumidity |
      | Moscow      | City        | Moscow, Russia      | en              | m           | Moscow       | Russia          | Moscow City    | 10               | N              | -5                 | 50              |
      | Vladivostok | City        | Vladivostok, Russia | en              | m           | Vladivostok  | Russia          | Primor'ye      | 25               | E              | -20                | 100             |
      | Limassol    | City        | Limassol, Cyprus    | en              | m           | Limassol     | Cyprus          | Limassol       | 0                | S              | 10                 | 90              |
      | Berlin      | City        | Berlin, Germany     | en              | m           | Berlin       | Germany         | Berlin         | 6                | W              | 0                  | 30              |

  Scenario: Check invalid API function request
    When We request invalid API function
    Then We check that API request is completed with status 200
    And We check that API request is completed with error:
      | code | 103                               |
      | type | invalid_api_function              |
      | info | This API Function does not exist. |

  Scenario: Check request with invalid access key
    When We get current weather with invalid API key
    Then We check that API request is completed with status 200
    And We check that API request is completed with error:
      | code | 101                                                                                     |
      | type | invalid_access_key                                                                      |
      | info | You have not supplied a valid API Access Key. [Technical Support: support@apilayer.com] |

  Scenario: Check request for historical weather
    When We get HISTORICAL weather for 'Moscow' query via rest API
    Then We check that API request is completed with status 200
    And We check that API request is completed with error:
      | code | 603                                                                                                                       |
      | type | historical_queries_not_supported_on_plan                                                                                  |
      | info | Your current subscription plan does not support historical weather data. Please upgrade your account to use this feature. |

  Scenario: Check request with additional params
    When We get CURRENT weather for 'Moscow' query via rest API with additional params:
      | language | unknown |
    Then We check that API request is completed with status 200
    And We check that API request is completed with error:
      | code | 105                                                                                    |
      | type | function_access_restricted                                                             |
      | info | Access Restricted - Your current Subscription Plan does not support this API Function. |