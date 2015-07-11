package ar.com.jss.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author sebastianscatularo@gmail.com.
 */
public class UserResource extends AbstractResouce {

    @Given("a user with credentials: $credentials")
    public void userCredentials(ExamplesTable credentials) {
        restOperations.getForObject("http://localhost:8080/user/1/", String.class);
    }

    @When("I request access")
    public void access() {

    }

    @Then("I get a valid token to talk with the system")
    public void verifyAccess() {

    }

    @Given("I am an authenticated user")
    public void authenticatedUser() {

    }

    @When("I want create a new account: $accountDetails")
    public void createAccount(ExamplesTable accountDetails) {

    }

    @Then("I see the account details")
    public void verifyAccountDetails() {

    }
}
