package ar.com.jss.service.data_access;

import ar.com.jss.model.domain.Transaction;
import org.springframework.hateoas.Resource;

import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com.
 */
public interface TransactionDataAccess {
    Collection<Resource<Transaction>> read(long account);

    Resource<Transaction> read(long account, long transaction);

    Resource<Transaction> create(long account, Transaction transaction);

    Resource<Transaction> update(long account, long transaction, Transaction transactionObject);

    void delete(long transaction);
}
