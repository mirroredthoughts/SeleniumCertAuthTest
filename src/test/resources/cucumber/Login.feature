@Login
  Feature: Sample Feature
    Background:
    Given User login as "admin"

    Scenario: User login as admin
      When user is logged in homepage
      And clicks log out