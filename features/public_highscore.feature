Feature: new public highscore

  As a app user
  I want to view the public highscore

  Scenario: View public highscore as an user works fine
    Given I press "Einloggen"
    And I enter "Hugo" into input field number 1
    And I enter "lustig" into input field number 2
    And I press "Einloggen"
    And I see "Hi, hugo"
    When I press "Oeffentlicher Highscore"
    And I should see "Leruka - Highscore"

  Scenario: View public highscore as a guest works fine
    Given I see "Leruka"
    When I press "Oeffentlicher Highscore"
    And I should see "Leruka - Highscore"