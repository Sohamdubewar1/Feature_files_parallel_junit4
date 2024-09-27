Feature: Verify Local test

  Scenario: Initialize Driver
    Given Driver is initiated

  Scenario: BStack Local Test - Navigate to Local App page
    Given I am on the website 'https://www.bstackdemo.com'


  Scenario: Quit Driver
    Then quit the driver