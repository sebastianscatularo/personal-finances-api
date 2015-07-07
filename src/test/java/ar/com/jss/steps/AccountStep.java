package ar.com.jss.steps;

import ar.com.jss.model.domain.Account;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Currency;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author sebastianscatularo@gmail.com.
 */
public class AccountStep {
    private ThreadLocal<Account> accountStore = new ThreadLocal<>();
    private String API_HOST = "http://localhost:8080/accounts";

    private RestTemplate restTemplate;

    public AccountStep() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    @Given("a new account with name $accountName with currency $currency")
    public void newAccountWithNameAndCurrency(String accountName, String currency) {
        Account account = new Account();
        account.setName(accountName);
        account.setCurrency(Currency.getInstance(currency));
        accountStore.set(account);
    }

    @When("I create a new account")
    public void newAccountAction() {
        Account account = accountStore.get();
        Resource<Account> create = restTemplate.postForObject(API_HOST, account, Resource.class);
        assertNotNull(create.getId());
    }



    @Then("the account is created")
    public void verifyAccountCreation() {

    }
}
