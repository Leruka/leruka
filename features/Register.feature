Feature: Register feature

  Scenario: Register works fine
    Given I wait for "Menu Guest" to appear
    When I press "Registrieren"
    And I enter "Opa" into input field number 1
    And I enter "123456" into input field number 2
    And I enter "123456" into input field number 3
    And I press "Registrieren"
    Then I should see "Menu User"


  Scenario: Register with different passwords
    Given I wait for "Menu Guest" to appear
    When I press "Registrieren"
    And I enter "Gustav" into input field number 1
    And I enter "123456" into input field number 2
    And I enter "123457" into input field number 3
    And I press "Registrieren"
    Then I should see "Register"


  Scenario: Register with already existing user
    Given I wait for "Menu Guest" to appear
    When I press "Registrieren"
    And I enter "Hugo" into input field number 1
    And I enter "123456" into input field number 2
    And I enter "123456" into input field number 3
    Then I should see "Register"