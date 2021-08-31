Feature: list
  Scenario: Get Details
    Given : I have created a list before
    And : have the corresponding list id
    And : I have a valid api key
    When : I send the query and the request
    Then : I get a confirmation
    And I get details of the list

  Scenario: Create List
    Given : I have a valid api key
    And : I have a session ID
    And : I have a name list
    And : I have a description list
    And : I have a language of the list
    When : I send the query and the requet
    Then : I get a confirmation
    And : I get a message
    And : A list ID

  Scenario: Add Movie
    Given : I have created a list before
    And : have the corresponding list id
    And : I have a valid api key
    And : I have a valid session ID
    When : I send the query and the request
    Then : I get a confirmation
    And : I get a message

  Scenario: Clear List
    Given : I have created a list before
    And : have the corresponding list id
    And : I have a valid api key
    When : I send the query
    Then : I get a confirmation
    And : I get a message

  Scenario: Deleted List
    Given : I have created a list before
    And : have the corresponding list id
    And : I have a valid api key
    When : I send the query
    Then : I get a confirmation
    And : I get a message