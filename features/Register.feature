Feature: Register feature

  Scenario: Register with different passwords
    Given I wait for "Leruka" to appear
    When I press "Registrieren"
    And I enter "Gustav" into input field number 1
    And I enter "123456" into input field number 2
    And I enter "123457" into input field number 3
    And I press "Registrieren"
    Then I should see "Registrieren"

  Scenario: Register with already existing user
    Given I wait for "Leruka" to appear
    When I press "Registrieren"
    And I enter "Hugo" into input field number 1
    And I enter "123456" into input field number 2
    And I enter "123456" into input field number 3
    Then I should see "Registrieren"

  Scenario: Register works fine
    Given I wait for "Leruka" to appear
    When I press "Registrieren"
    And I enter "Oma1" into input field number 1
    And I enter "123456" into input field number 2
    And I enter "123456" into input field number 3
    And I press "Registrieren"
    Then I should see "Erfolg"