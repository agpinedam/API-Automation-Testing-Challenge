Feature: Authentication movieDB
  Scenario: Create valid guest session
    Given : I have an valid api key
    When : I send a query with the api key
    Then : I get a confirmation
    And : A guest session ID
    And : A date of session ID expiration

  Scenario: Create valid Request Token
    Given : I have an valid api key
    When : I send a query with the api key
    Then : I get a confirmation
    And : A guest a guest request token
    And : A date of token expiration

  Scenario: Create valid session
    Given : I have an valid api key
    And : I have and token request
    When : I send the query and the request
    Then : I get a confirmation
    And : A session ID

  Scenario: Create valid session with Loggin
    Given : I have an valid api key
    And : I have and token request
    And : I have and valid user name
    And : I have the corresponding password
    When : I send the query and the request
    Then : I get a confirmation
    And : A date of expiration
    And : A request token

  Scenario: Create Session (from v4 access token)
    Given : I have an valid api key
    And : I have a token request
    When : I send the query and the request
    Then : I get a confirmation
    And : A session ID

  Scenario: Delete Session
    Given : I have created a session ID
    And : have an valid api key
    And : I have a valid session ID
    When : I send a query and a request
    Then : I get a confirmation
