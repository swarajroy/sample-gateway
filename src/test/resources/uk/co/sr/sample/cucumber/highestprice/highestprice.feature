Feature: Get the highest price

  Scenario: Get the highest price
    When I call the highest price API endpoint with currency "USD"
    Then I get a response of 200
    And the response matches "highestprice"