package ar.com.jss.steps;

import ar.com.jss.model.domain.Account;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.hateoas.Resource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com.
 */
public class AccountStep {
    private String account = "http://localhost:8080/accounts";

    private RestTemplate restTemplate;

    public AccountStep() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    @Given("As an user")
    public void userAction() {

    }

    @When("I create a new account")
    public void newAccountAction() {
        Collection<Resource<Account>> resources = restTemplate.getForObject(account, Collection.class);
        Resource<Account> create = restTemplate.postForObject(account, null, Resource.class);
        create.getId();
    }

    @Then("the account is created")
    public void verifyAccountCreation() {

    }
}
