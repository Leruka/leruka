Feature: View personal highscore

  Scenario: View personal highscore works fine
    Given I am logged in
    And I press "Persönlicher Highscore"
    Then I see "Meine Top5"

  Scenario: View personal highscore fails
    Given I am not logged in
    And I don't see "Persönlicher Highscore"
    Then I should see "Leruka"