
Feature: Ordering Products in the E-commerce

I want to use this template for my feature file

Background:

Given I Landed on the E-commerce page

@Regression

Scenario Outline: Purchasing product in the E-commerce

Given logged in to the E-commerce application with user name "<email>" and password "<password>"
When Adding the product "<productName>" to the cart
And Checkout the product "<productName>" place the order
Then confirmation message "THANKYOU FOR THE ORDER." visible

Examples:

| email | password | productName |
| qatest1@yopmail.com | Test@123 | IPHONE 13 PRO |