package ar.com.jss.service.assambler;

import ar.com.jss.controller.AccountController;
import ar.com.jss.model.domain.AccountResource;
import ar.com.jss.model.domain.Account;
import ar.com.jss.model.repository.entity.AccountEntity;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Service
public class AccountAssembler extends ResourceAssemblerSupport<AccountEntity, AccountResource> {
    public AccountAssembler() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(AccountEntity entity) {
        AccountResource resource = createResourceWithId(entity.getId(), entity, 1);
        resource.setName(entity.getName());
        resource.setCurrency(entity.getCurrency());
        resource.setAmount(BigDecimal.ZERO);
        return resource;
    }
}
