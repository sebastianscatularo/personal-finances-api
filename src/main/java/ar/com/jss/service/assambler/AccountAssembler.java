package ar.com.jss.service.assambler;

import ar.com.jss.controller.AccountController;
import ar.com.jss.controller.TransactionController;
import ar.com.jss.model.domain.Account;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.core.LinkBuilderSupport;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Service
public class AccountAssembler implements ResourceAssembler<Account, Resource<Account>> {
    @Override
    public Resource<Account> toResource(Account entity) {
        Resource<Account> accountResource = new Resource<>(entity);
        accountResource.add(linkTo(methodOn(AccountController.class).account(1, entity.getId())).withSelfRel());
        //accountResource.add(linkTo(methodOn(TransactionController.class).transactions(entity.getId())).withRel("transactions"));
        return accountResource;
    }
}
