Meta:
Narrative:
As a user
I want to create a new account
So I can track my finances

GivenStories: story/access.story

Scenario: Create a new account

Given a new account with name TEST-ACCOUNT and with currency ARS
When I create a new account
Then I verify that the account was created successful
