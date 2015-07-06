package ar.com.jss.model.repository;

import ar.com.jss.model.domain.Transaction;
import ar.com.jss.model.repository.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author sebastianscatularo@gmail.com.
 */
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
}
