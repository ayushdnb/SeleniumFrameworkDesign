Feature: Error Validations in Login and Cart

  @ErrorHandling
  Scenario: Login error message validation with incorrect credentials
    Given I am on the landing page
    When I login with invalid credentials
      | email            | password         |
      | a113bc@gmail.com | Rahu2lshetty@1   |
    Then I should see an error message "Incorrect email or password."

  Scenario: Product not displayed in cart when added name mismatches
    Given I am logged in with valid credentials
      | email           | password         |
      | a13bc@gmail.com | Rahulshetty@1    |
    When I add product "ZARA COAT 3" to the cart
    And I go to the cart page
    Then I should not see product "ZARA COAT 33" in the cart
