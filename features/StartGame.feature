Feature: Start game

  Scenario: I want to start the game
    Given I wait for "Leruka" to appear
    When I press "Spiel starten"
    Then I should see "GameMainActivity"