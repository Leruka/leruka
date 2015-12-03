Feature: View Instruction feature
  Scenario: I want to see the view instruction
    Given I wait for "Leruka" to appear
    When I press "Spieleanleitung"
    Then I should see "Spieleanleitung"


