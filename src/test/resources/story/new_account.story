Meta:
Narrative:
As a user
I want to create a new account
So I can track my finances

Scenario: Create a new account

Given a new account with name TEST-ACCOUNT with currency ARS
When I create a new account
Then the account is created
