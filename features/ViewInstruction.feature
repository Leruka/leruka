Feature: View Instruction feature
  Scenario: I want to see the view instruction
    Given I wait for "Menu Guest" to appear
    When I press "Spieleanleitung"
    Then I wait for the "Spieleanleitung" view to appear


