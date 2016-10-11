Feature: Setup TestData

  Scenario: Write to JSON file
    Given I write data to JSON file:users key:test and value:testings stuff
    And I read data from JSON file:users key:test