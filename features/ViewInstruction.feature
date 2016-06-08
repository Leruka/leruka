Feature: View Instruction feature

  As a guest
  I want to view the instruction

  Scenario: View instruction works fine
    Given I see "Leruka"
    When I press "Spielanleitung"
    Then I should see "Spielanleitung"

  Scenario: View instruction fails
    Given I see "Leruka"
    When I press not "Spielanleitung"
    Then I should see "Leruka"

  As a user
  I want to view the instruction

  Scenario: view instruction works fine
    Given I press "Einloggen"
    And I enter "Hugo" into input field number 1
    And I enter "lustig" into input field number 2
    And I press "Einloggen"
    When I press "Spielanleitung"
    Then I should see "Spielanleitung"

