package ar.com.jss.model.repository;

import ar.com.jss.model.repository.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author sebastianscatularo@gmail.com.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}
