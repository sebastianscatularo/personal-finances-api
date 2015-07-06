package ar.com.jss.service.assambler;

import ar.com.jss.controller.TransactionController;
import ar.com.jss.model.domain.Transaction;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Service
public class TransactionAssembler implements ResourceAssembler<Transaction, Resource<Transaction>> {
    /**
     * Converts the given entity into an {@link ResourceSupport}.
     *
     * @param entity
     * @return
     */
    @Override
    public Resource<Transaction> toResource(Transaction entity) {
        Resource<Transaction> resource = new Resource<>(entity);
        resource.add(linkTo(methodOn(TransactionController.class).getTransaction(entity.getAccountId(), entity.getId())).withSelfRel());
        return resource;
    }
}
