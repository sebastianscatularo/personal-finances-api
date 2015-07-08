package ar.com.jss.service.data_access;

import ar.com.jss.model.domain.User;
import org.springframework.hateoas.Resource;

import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com.
 */
public interface UserDataAccess {
    Collection<Resource<User>> getUsers();

    Resource<User> getUser(long user);
}
