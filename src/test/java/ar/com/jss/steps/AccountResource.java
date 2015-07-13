package ar.com.jss.steps;

import ar.com.jss.domain.AccountWrapper;
import ar.com.jss.domain.UserWrapper;
import ar.com.jss.model.domain.Account;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.web.client.RestOperations;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author sebastianscatularo@gmail.com.
 */
public class AccountResource extends HttpResource {
    @Given("a new account with name $accountName and with currency $accountCurrency")
    public void newAccount(String accountName, String accountCurrency) {
        Account account = new Account();
        account.setCurrency(Currency.getInstance(accountCurrency));
        account.setName(accountName);
        put("account", account);
        put("accountName", accountName);
        put("currency", accountCurrency);
    }

    @When("I create a new account")
    public void createNewAccount() {
        Collection<UserWrapper> users = get("users");
        String accountsUri = users.iterator().next().getLinks().get("accounts").getHref();
        Account account = get("account");
        RestOperations client = get("restClient");
        AccountWrapper accountResource = client.postForObject(accountsUri, account, AccountWrapper.class);
        client.put(accountResource.getLinks().get("self").getHref(), account);
    }

    @Then("I verify that the account was created successful")
    public void verifyAccountCreation() {
        RestOperations client = get("restClient");
        Collection<UserWrapper> users = get("users");
        String accountsUri = users.iterator().next().getLinks().get("accounts").getHref();
        AccountWrapper[] collection = client.getForObject(accountsUri, AccountWrapper[].class);
        assertThat(collection.length, is(1));
        assertThat(collection[0].getName(), is(get("accountName")));
        assertThat(collection[0].getCurrency().getCurrencyCode(), is(get("currency")));
        assertThat(collection[0].getAmount(), is(BigDecimal.ZERO));
    }
}
