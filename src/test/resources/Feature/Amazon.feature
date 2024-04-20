Feature: Validate phone prices

  Background: Navigate to the amazon home page
    Given I am on "amazon" home page

  Scenario: get amazon 5-10 mobile phone list
    When I click on the "Mobiles" button
    And I click on the "₹5,000 - ₹10,000" button
    Then I print all devices names and prices


  Scenario: get amazon 10-20k mobile phone list
    When I click on the "Mobiles" button
    And I click on the "₹10,000 - ₹20,000" button
    Then I print all devices names and prices


  Scenario: get amazon Over ₹20,000 mobile phone list
    When I click on the "Mobiles" button
    And I click on the "Over ₹20,000" button
    Then I print all devices names and prices