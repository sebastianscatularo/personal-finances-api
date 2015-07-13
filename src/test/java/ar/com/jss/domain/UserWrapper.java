package ar.com.jss.domain;

import ar.com.jss.model.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;

import java.util.Map;

/**
 * @author sebastianscatularo@gmail.com.
 */
public class UserWrapper extends User {
    @JsonProperty("_links")
    private Map<String, Link> links;

    public Map<String, Link> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Link> links) {
        this.links = links;
    }
}
