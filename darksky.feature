@regression @darksky
Feature: Darksky feature

  Background:
    Given I am on Darksky home page

  @darksky-1


  Scenario: Verify timeline is displayed in correct format
    And I verify timeline current time Now is displayed
    Then I verify timeline is displayed with two hours incremented

  @darksky-2

  Scenario: Verify individual day temp timeline
    When I expand todays timeline
    Then I verify lowest and highest temp is displayed correctly

  @darksky-3

  Scenario: Verify Current Temperature should not be greater or less than the Temperature from Daily Timeline
    Then I verify current temp is not greater or less then temps from daily timeline