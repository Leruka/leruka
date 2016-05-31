Feature: View personal highscore
  As a app user
  I want to see my highscore

  Scenario: View personal highscore as a user works fine
    Given I press "Einloggen"
    And I enter "Hugo" into input field number 1
    And I enter "lustig" into input field number 2
    And I press "Einloggen"
    And I see "Hi, hugo"
    And I press "Persoenlicher Highscore"
    Then I see "Dein Highscore"

  Scenario: View personal highscore fails
    Given I see "Willkommen zu LeRuKa"
    And I don't see "Persoenlicher Highscore"
    Then I should see "Leruka"