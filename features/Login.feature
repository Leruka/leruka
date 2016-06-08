Feature: Login feature

  As a member
  I want to login

  Scenario: Login works fine
    Given I wait for "Leruka" to appear
    When I press "Einloggen"
    And I enter "Hugo" into input field number 1
    And I enter "lustig" into input field number 2
    And I press "Einloggen"
    Then I should see "Hi, Hugo"

  Scenario: Login fails
    Given I wait for "Leruka" to appear
    When I press "Einloggen"
    And I enter "Hugo" into input field number 1
    And I enter "123456" into input field number 2
    And I press "Einloggen"
    Then I should see "Anmelden"