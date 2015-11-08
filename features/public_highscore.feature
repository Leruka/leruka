Feature: new public highscore
  as a visitor
  i want to view the public highscore

  Scenario: view public highscore
    Given I started the app
    When I press "Öffentlicher Highscore"
    And score data is in the database
    Then I should see the public highscore
