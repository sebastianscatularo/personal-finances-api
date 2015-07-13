Narrative:
As a user with credentials
I want logged in
So that I can do thing in the system

Scenario: Access with valid credentials

Given I am a user with credentials:
|client-id  |client-secret  |grant-type |scope      |username           |password   |
|clientapp  |123456         |password   |read write |sebastian@test.com |spring     |
When I try to access to the list of users
Then I verify that the list contains just one user