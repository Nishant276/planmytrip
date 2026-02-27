Feature: MakeMyTrip Train Booking Automation
  As a user
  I want to search for trains from Ballia to Sealdah
  So that I can check seat availability and book a train

  Scenario: Search for trains from Ballia to Sealdah and verify seat availability
    Given I navigate to MakeMyTrip website
    When I click on the Trains tab
    And I enter "Ballia" as the source station
    And I enter "Sealdah" as the destination station
    And I select the journey date as tomorrow
    And I click on the Search button
    Then I should see a list of available trains
    And If trains are available then I should mark the test as "good"
    And If no trains are available then I should throw an error message

  Scenario: Verify train details for Ballia to Sealdah route
    Given I navigate to MakeMyTrip website
    When I click on the Trains tab
    And I enter "Ballia" as the source station
    And I enter "Sealdah" as the destination station
    And I select the journey date as tomorrow
    And I click on the Search button
    Then the trains list should display with passenger details
    And each train should show seat availability information
