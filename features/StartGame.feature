Feature: Start game

  Scenario: I want to start the game
    Given I started the app
    And I press "Spiel starten"
    Then I should see "TestGestureActivity"