Feature: Change Settings
  As a logged in user
  I want to change my settings

  Scenario: change password works fine
    Given I started the app
    And I am logged in
    When I click the button "Einstellungen"
    And I click the button "Passwort ändern"
    And I fill out all required fields
    And I click "bestätigen"
    And all input fields are valid
    Then I want the password to be changed
    But if one input element is not valid
    Then The password should not be changed
    And I must correct the invalid input fields

  Scenario: change display name works fine
    Given I started the app
    And I am logged in
    When I click the button "Einstellungen"
    And I click the button "Name ändern"
    And I fill out all required fields
    And I click "bestätigen"
    And the display name is available
    Then I want the display name to be changed
    And I want the name in the public highscore to be changed
    But if the display name is not available
    Then The name should not to be changed

  Scenario: Cancel change the password
    Given I started the app
    And I am logged in
    When I click the button "Einstellungen ändern"
    And I click the button "Passwort ändern"
    And I fill in the required fields
    And I click "abbrechen"
    Then I want to see the "Einstellungen" page
    And I want the password not to be changed

  Scenario: Cancel change the display name
    Given I am logged in
    When I click the button "Einstellungen ändern"
    And I click the button "Name ändern"
    And I fill in the fields
    And I click "abbrechen"
    Then I want to see the "Enstellungen" page
    And I want my display name not to be changed
