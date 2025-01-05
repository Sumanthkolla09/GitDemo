@tag
  Feature: Purchase the order from Ecommerce Website

    Background :
      Given  I landed on Ecommerce Page

    @tag2
    Scenario Outline: Positive test for submitting the order

      Given  Logged in with username <name> and password <password>
      When  I add the product <productname> to Cart
      And Checkout <productname> and submit the order
      Then "India" country should be selected

      Examples:
        | name                | password | productname   |
        | sumanth@testing.com | Demo@123 | IPHONE 13 PRO |
