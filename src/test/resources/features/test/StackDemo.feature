Feature: BrowserStack Demo

  Scenario: Initialize Driver
    Given Driver is initiated

  Scenario: BStack Sample Test - Add product to cart 1
    Given I am on the website 'https://www.bstackdemo.com'
    When I select a product and click on 'Add to cart' button
    Then the product should be added to cart

  Scenario: BStack Sample Test - Add product to cart 2
    Given I am on the website 'https://www.bstackdemo.com'
    When I select a product and click on 'Add to cart' button
    Then the product should be added to cart

  Scenario: Quit Driver
    Then quit the driver
