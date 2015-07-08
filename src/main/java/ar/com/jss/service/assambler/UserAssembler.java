package ar.com.jss.service.assambler;

import ar.com.jss.model.domain.User;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Service;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Service
public class UserAssembler implements ResourceAssembler<User, Resource<User>> {
    /**
     * Converts the given entity into an {@link ResourceSupport}.
     *
     * @param entity
     * @return
     */
    @Override
    public Resource<User> toResource(User entity) {
        return new Resource<>(entity);
    }
}
