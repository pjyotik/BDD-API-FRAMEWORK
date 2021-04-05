Feature: Validating Google API

  @AddPlace
  Scenario Outline: Verify if a Place is being added successfully using AddPlace API
    Given I have a Payload to Add Place with "<Name>" "<Language>" "<Address>" "<PhoneNumber>"
    When I make a calls to the "AddPlaceAPI" with "POST" http request
    Then I should gets the http status code as 200
    And the "status" in response body is "OK"
    And the "scope" in response body is "APP"
    And verify place_ID created maps to "<Name>" using "GetPlaceAPI"
    Examples:
      | Name  | Language | Address      | PhoneNumber |
      | TESTA | ENGLISH  | BAKER STREET | 9840998949  |
      #| TESTB | FRENCH   | REGEN STREET | 9840998959  |

  @DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given I have a Payload to Delete Place
    When I make a calls to the "DeletePlaceAPI" with "POST" http request
    Then I should gets the http status code as 200
    And "status" in response body is "OK"

