package ar.com.jss.service.data_access;

import ar.com.jss.model.domain.Account;
import ar.com.jss.model.domain.AccountResource;
import org.springframework.hateoas.Resource;

import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com.
 */
public interface AccountDataAccess {
    Collection<AccountResource> read(long user);

    AccountResource read(long user, long account);

    AccountResource create(long user, AccountResource account);

    AccountResource update(long accountId, AccountResource account);

    void delete(long account);
}
