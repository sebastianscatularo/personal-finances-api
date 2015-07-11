Narrative:
As a user with valid credentials
I want get a token
So that I can do thing in the system

Scenario: Access with valid credentials

Given a user with credentials:
|application|access-id  |
|restapp    |123456     |
When I request access
Then I get a valid token to talk with the system