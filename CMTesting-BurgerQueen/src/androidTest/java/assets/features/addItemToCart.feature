Feature: add item to cart

  Background: A user is signed into the app.
    Given that I have launched the app.
    And a user named "oxime" exists.
    And I am signed in as that user.

  Scenario Outline: A user adds <additional> <item> orders to their cart.
    Given I am on the "Home tab".
    And I press the login button.
    When I attempt to login as user "oxime" with password "yes".
    When I press the "<item>" order.
    And there are <initial> <item> orders in my cart.
    When I add  <additional> <item> orders of to my cart.
    And I click on the cart tab.
    Then I should see <result> <item> orders in my cart.
    Then I take a screenshot.


    Examples:
      | item                | initial | additional | result |
      | BACON QUEEN         | 0       | 1          | 1      |
      | TRIPLE BURGER       | 1       | 2          | 3      |
      | QUARTER POUND QUEEN | 1000    | 232        | 1232   |



