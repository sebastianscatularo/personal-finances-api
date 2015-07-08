package ar.com.jss.service.assambler;

import ar.com.jss.controller.AccountController;
import ar.com.jss.model.domain.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
        Resource<User> user = new Resource<>(entity);
        user.add(linkTo(methodOn(AccountController.class).accounts(entity.getId())).withRel("accounts"));
        return user;
    }
}
