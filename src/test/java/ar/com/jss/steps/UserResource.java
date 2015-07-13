package ar.com.jss.steps;

import ar.com.jss.domain.UserWrapper;
import ar.com.jss.model.domain.User;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author sebastianscatularo@gmail.com.
 */
public class UserResource extends HttpResource {

    @Given("I am a user with credentials: $credentials")
    public void userCredentials(ExamplesTable table) {
        Map<String, String> credentials = table.getRow(0);
        ResourceOwnerPasswordResourceDetails resourceCredentials = new ResourceOwnerPasswordResourceDetails();
        resourceCredentials.setUsername(credentials.get("username"));
        resourceCredentials.setPassword(credentials.get("password"));
        resourceCredentials.setClientId(credentials.get("client-id"));
        resourceCredentials.setClientSecret(credentials.get("client-secret"));
        resourceCredentials.setScope(asList(credentials.get("scope").split(" ")));
        resourceCredentials.setGrantType(credentials.get("grant-type"));
        resourceCredentials.setAccessTokenUri("http://localhost:8080/oauth/token");
        put("restClient", new OAuth2RestTemplate(resourceCredentials));
    }

    @When("I try to access to the list of users")
    public void listUsers() {
        URI usersUri = UriComponentsBuilder
                .fromUri(HOST_URI)
                .path("/users")
                .build()
                .toUri();
        RestOperations client = get("restClient");
        Collection<UserWrapper> users = asList(client.getForObject(usersUri, UserWrapper[].class));
        put("users", users);
    }

    @Then("I verify that the list contains just one user")
    public void verifyAccess() {
        Collection<UserWrapper> users = get("users");
        assertThat("The list of users should not be empty", users.isEmpty(), is(false));
        assertThat("The list of users should contains just one user", users.size(), is(1));
    }
}
