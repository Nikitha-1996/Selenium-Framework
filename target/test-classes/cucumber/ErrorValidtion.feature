
Feature: ErrorValidation 
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: check Validation Error 
    Given I Landed on the E-commerce page
    When logged in to the E-commerce application with user name "<email>" and password "<password>"
    Then error message "Incorrect email or password." is visible

 Examples:

| email              | password |
| qatest@yopmail.com | Test@123 |
