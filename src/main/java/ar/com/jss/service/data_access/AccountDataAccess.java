package ar.com.jss.service.data_access;

import ar.com.jss.model.domain.Account;
import org.springframework.hateoas.Resource;

import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com.
 */
public interface AccountDataAccess {
    Collection<Resource<Account>> read();

    Resource<Account> read(long account);

    Resource<Account> create(long user, Account account);

    Resource<Account> update(Account account);

    void delete(long account);
}
