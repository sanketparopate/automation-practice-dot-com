Feature: New user registration

  Scenario: Create a new user with all fields
    Given User validates landing page
    And User navigates to Create Account page
    And User enters Email "test@gmail.com" and navigates to Account details page
    When Create a new user with following details
      | Title          | Mr.                              |
      | FirstName      | Test                             |
      | LastName       | User                             |
      | Password       | GetPassword                      |
      | DOBDate        | 29                               |
      | DOBMonth       | Feb                              |
      | DOBYear        | 1988                             |
      | Newsletter     | Yes                              |
      | Offers         | No                               |
      | Company        | Automation                       |
      | Address1       | Wall Street                      |
      | Address2       | Park Avenue                      |
      | City           | San Jose                         |
      | State          | California                       |
      | ZipCode        | 95008                            |
      | Country        | United States                    |
      | AdditoinalInfo | This is a automated test Account |
      | HomePhone      | 6655443322                       |
      | Mobile         | 3344556677                       |
      | AddressAlias   | Home                             |
    Then User validates account creation is successful

  Scenario: Create a new user with mandatory fields only
    Given User validates landing page
    And User navigates to Create Account page
    And User enters Email "test@gmail.com" and navigates to Account details page
    When Create a new user with following details
      | FirstName      | Test                             |
      | LastName       | User                             |
      | Password       | GetPassword                      |
      | Address1       | Wall Street                      |
      | City           | San Jose                         |
      | State          | California                       |
      | ZipCode        | 95008                            |
      | Country        | United States                    |
      | Mobile         | 3344556677                       |
      | AddressAlias   | Home                             |
    Then User validates account creation is successful

  Scenario: Unable to create a new user due to missing mandatory fields and validate error messages
    Given User validates landing page
    And User navigates to Create Account page
    And User enters Email "test@gmail.com" and navigates to Account details page
    When Create a new user with following details
      | FirstName      | Test                             |
      | LastName       | User                             |
      | Password       | GetPassword                      |
      | State          | California                       |
      | ZipCode        | 95008                            |
      | Country        | United States                    |
      | Mobile         | 3344556677                       |
      | AddressAlias   | Home                             |
    Then User validates error is displayed
    And Error message "address1 is required." is displayed
    And Error message "city is required." is displayed

  Scenario: Unable to create a new user - deliberate failure to capture screenshot
    Given User validates landing page
    And User navigates to Create Account page
    And User enters Email "test@gmail.com" and navigates to Account details page
    When Create a new user with following details
      | FirstName      | Test                             |
      | LastName       | User                             |
      | Password       | GetPassword                      |
      | City           | San Jose                         |
      | State          | California                       |
      | ZipCode        | 95008                            |
      | Country        | United States                    |
      | Mobile         | 3344556677                       |
      | AddressAlias   | Home                             |
    Then User validates error is displayed
    And Error message "city is required." is displayed

  Scenario: Create new user - negative - failure expected
    Given User validates landing page
    And User navigates to Create Account page
    And User enters Email "test@gmail.com" and navigates to Account details page
    When Create a new user with following details
      | Title          | Mr.                              |
      | FirstName      | Test                             |
      | LastName       | User                             |
      | Password       | GetPassword                      |
      | DOBDate        | 29                               |
      | DOBMonth       | Feb                              |
      | DOBYear        | 1988                             |
      | Newsletter     | Yes                              |
      | Offers         | No                               |
      | Company        | Automation                       |
      | City           | San Jose                         |
      | State          | California                       |
      | ZipCode        | 95008                            |
      | Country        | United States                    |
      | AdditoinalInfo | This is a automated test Account |
      | HomePhone      | 6655443322                       |
      | Mobile         | 3344556677                       |
      | AddressAlias   | Home                             |
    Then User validates account creation is successful

