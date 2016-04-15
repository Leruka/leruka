Feature: Change Settings
  As a logged in user
  I want to change my settings

  Background:
    Given a user with the display name "Henry"
    And the password "password123"

  Scenario: change password works fine
    Given I started the app
    And I am logged in as "Henry"
    When I click the button "Einstellungen"
    And I click the button "Passwort ändern"
    And I fill in "password123" in the field "Altes Passwort"
    And I fill in "1234" in the field "Neues Passwort"
    And I fill in "1234" in the field "Neues Passwort wiederholen"
    And I click "Speichern"
    Then the password will be changed in the database

    

  Scenario: change password with invalid old password
    Given I started the app
    And I am logged in as "Henry"
    When I click the button "Einstellungen"
    And I click the button "Passwort ändern"
    And I fill in "password" in the field "Altes Passwort"
    And I fill in "1234" in the field "Neues Passwort"
    And I fill in "1234" in the field "Neues Passwort wiederholen"
    And I click "Speichern"
    Then the password will not be changed in the database
    And I will see the page "Passwort ändern"
    And I will see an error message "Eingabe ungültig!"

  Scenario: change password with invalid new password
    Given I started the app
    And I am logged in as "Henry"
    When I click the button "Einstellungen"
    And I click the button "Passwort ändern"
    And I fill in "password123" in the field "Altes Passwort"
    And I fill in "1234" in the field "Neues Passwort"
    And I fill in "5678" in the field "Neues Passwort wiederholen"
    And I click "Speichern"
    Then the password will not be changed in the database
    And I will see the page "Passwort ändern"
    And I will see an error message "Eingabe ungültig!"



  Scenario: change display name works fine
    Given I started the app
    And I am logged in as "Henry"
    When I click the button "Einstellungen"
    And I click the button "Name ändern"
    And I fill in field "Name" the new display name "Leon"
    And I click "Speichern"
    And the display name is available
    Then The display name want to be changed
    And The name in the public highscore want to be changed


  Scenario: change display name with name in use
    Given I started the app
    And I am logged in as "Henry"
    When I click the button "Einstellungen"
    And I click the button "Name ändern"
    And I fill in field "Name" the new display name "Tina"
    And I click "Speichern"
    And the display name is not available
    Then The display name want not to be changed
    And I want to see the "Anzeigenamen ändern" page
    And I will see an error message "Name schon vergeben!"


  Scenario: Cancel change the password
    Given I started the app
    And I am logged in
    When I click the button "Einstellungen ändern"
    And I click the button "Passwort ändern"
    And I fill in the required fields
    And I click "Zurück"
    Then I want to see the "Einstellungen" page
    And I want the password not to be changed

  Scenario: Cancel change the display name
    Given I am logged in
    When I click the button "Einstellungen ändern"
    And I click the button "Name ändern"
    And I fill in the fields
    And I click "Zurück"
    Then I want to see the "Einstellungen" page
    And I want my display name not to be changed
