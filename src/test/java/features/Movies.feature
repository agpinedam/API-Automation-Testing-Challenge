Feature: Movies
  Scenario: Get Details
    Given : I have a movie id
    And : I have a valid api key
    When : I send the query
    Then : I get a confirmation
    And : I get the details of the movie

  Scenario: Rate Movie
    Given : I have a movie id
    And : I have a valid api key
    When : I send the query and the request
    Then : I get a confirmation
    And : I get a message