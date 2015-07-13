package ar.com.jss.service.data_access;

import ar.com.jss.model.domain.User;
import ar.com.jss.model.domain.UserResource;
import org.springframework.hateoas.Resource;

import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com.
 */
public interface UserDataAccess {
    Collection<UserResource> getUsers();
    UserResource getUser(long user);
}
