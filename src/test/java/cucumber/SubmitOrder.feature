@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
	Background:
		Given I landed on Ecommerce Page
  @tag2
  Scenario Outline: Positive test for submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> from Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name            | password 		 |productName|
      | a13bc@gmail.com | Rahulshetty@1|ZARA COAT 3|
      
