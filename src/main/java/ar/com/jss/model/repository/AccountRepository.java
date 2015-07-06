package ar.com.jss.model.repository;

import ar.com.jss.model.repository.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author sebastianscatularo@gmail.com.
 */

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
}
