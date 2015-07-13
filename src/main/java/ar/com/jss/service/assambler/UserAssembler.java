package ar.com.jss.service.assambler;

import ar.com.jss.controller.AccountController;
import ar.com.jss.controller.UserController;
import ar.com.jss.model.domain.User;
import ar.com.jss.model.domain.UserResource;
import ar.com.jss.model.repository.entity.UserEntity;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Service
public class UserAssembler extends ResourceAssemblerSupport<UserEntity, UserResource> {
    public UserAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(UserEntity entity) {
        UserResource resource = createResourceWithId(entity.getId(), entity);
        resource.add(linkTo(methodOn(AccountController.class).accounts(entity.getId())).withRel("accounts"));
        return resource;
    }
}
