Feature: User feature

  @regression
  Scenario Outline: Validate user creation with valid payload is successful
    Given Post a user using these inputs "<username>" and "<password>"
    Then Verify status code is 201
    And Verify response contains correct "<username>"
    And Delete user with "<username>" and "<password>"
    Examples:
      | username   | password |
      | ElmUser007 | Pass123! |

    



    