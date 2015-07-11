package ar.com.jss.steps;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.client.RestOperations;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author sebastianscatularo@gmail.com.
 */
public class AbstractResouce {
    protected RestOperations restOperations;
    public AbstractResouce() {
        ResourceOwnerPasswordResourceDetails cre = new ResourceOwnerPasswordResourceDetails();
        cre.setAccessTokenUri("http://localhost:8080/oauth/token");
        cre.setClientId("clientapp");
        cre.setClientSecret("123456");
        cre.setGrantType("password");
        cre.setScope(newArrayList("read", "write"));
        cre.setPassword("spring");
        cre.setUsername("sebastian@test.com");
        restOperations = new OAuth2RestTemplate(cre);
    }
}
