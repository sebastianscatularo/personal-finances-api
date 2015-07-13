package ar.com.jss.steps;

import ar.com.jss.model.domain.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.Resource;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author sebastianscatularo@gmail.com.
 */
abstract class HttpResource {
    public static final URI HOST_URI = URI.create("http://localhost:8080");
    private static ThreadLocal<Map<String, Object>> store = new ThreadLocal<>();
    public static void put(String key, Object value) {
        if (store.get() == null) {
            store.set(new ConcurrentHashMap<String, Object>());
        }
        store.get().put(key, value);
    }

    public static <T> T get(String key) {
        return (T) store.get().get(key);
    }
}
