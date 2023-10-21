Feature: Login Aplikasi SauceDemo

  @Login @Positive
  Scenario: Success login
    Given Halaman login SauceDemo
    When Input username
    And Input password
    And Click login button
    Then User is on product page

  @Login @Negative
  Scenario: Failed login
    Given Halaman login SauceDemo
    When Input username
    And Input invalid password
    And Click login button
    Then User get error message