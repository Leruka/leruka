Feature: Change Settings
  As a logged in user
  I want to change my settings


  Scenario: change password with invalid old password
    Given I press "Einloggen"
    And I enter "Opa" into input field number 1
    And I enter "123456" into input field number 2
    And I press "Einloggen"
    When I press "Einstellungen"
    And I enter "123456" into input field number 2
    And I enter "123456" into input field number 3
    And I enter "123457" into input field number 4
    And I press "Speichern"
    Then I should see "Einstellungen"


  Scenario: change password with invalid new password
    Given I press "Einloggen"
    And I enter "Opa" into input field number 1
    And I enter "123456" into input field number 2
    And I press "Einloggen"
    When I press "Einstellungen"
    And I enter "123456" into input field number 2
    And I enter "123457" into input field number 3
    And I enter "123456" into input field number 4
    And I press "Speichern"
    Then I should see "Einstellungen"


  Scenario: change display name with name in use
    Given I press "Einloggen"
    And I enter "Opa" into input field number 1
    And I enter "123456" into input field number 2
    And I press "Einloggen"
    When I press "Einstellungen"
    And I enter "Opa3" into input field number 1
    And I enter "123456" into input field number 4
    And I press "Speichern"
    Then I should see "Einstellungen"


  Scenario: Cancel change the password
    Given I press "Einloggen"
    And I enter "Opa" into input field number 1
    And I enter "123456" into input field number 2
    And I press "Einloggen"
    When I press "Einstellungen"
    And I enter "123456" into input field number 2
    And I enter "123456" into input field number 3
    And I enter "111111" into input field number 4
    And I press "Abbrechen"
    Then I should see "Leruka"

  Scenario: Cancel change the display name
    Given I press "Einloggen"
    And I enter "Opa" into input field number 1
    And I enter "123456" into input field number 2
    And I press "Einloggen"
    When I press "Einstellungen"
    And I enter "Opa1" into input field number 1
    And I enter "111111" into input field number 4
    And I press "Abbrechen"
    Then I should see "Leruka"


  Scenario: change display name works fine
    Given I press "Einloggen"
    And I enter "Opa" into input field number 1
    And I enter "123456" into input field number 2
    And I press "Einloggen"
    When I press "Einstellungen"
    And I enter "Opa2" into input field number 1
    And I enter "123456" into input field number 4
    And I press "Speichern"
    Then I should see "Erfolg"

  Scenario: change password works fine
    Given I press "Einloggen"
    And I enter "Opa2" into input field number 1
    And I enter "123456" into input field number 2
    And I press "Einloggen"
    When I press "Einstellungen"
    And I enter "111111" into input field number 2
    And I enter "111111" into input field number 3
    And I enter "123456" into input field number 4
    And I press "Speichern"
    Then I should see "Erfolg"
