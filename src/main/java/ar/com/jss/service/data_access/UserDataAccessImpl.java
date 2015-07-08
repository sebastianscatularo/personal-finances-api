package ar.com.jss.service.data_access;

import ar.com.jss.model.domain.User;
import ar.com.jss.model.repository.UserRepository;
import ar.com.jss.model.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Service
public class UserDataAccessImpl implements UserDataAccess {
    private final ResourceAssembler<User, Resource<User>> assembler;

    @Autowired
    public UserDataAccessImpl(ResourceAssembler<User, Resource<User>> assembler) {
        this.assembler = assembler;
    }

    @Override
    public Collection<Resource<User>> getUsers() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return asList(assembler.toResource(userEntity.toUser()));
    }

    @Override
    public Resource<User> getUser(long user) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == userEntity.getId()) {
            return assembler.toResource(userEntity.toUser());
        }
        throw new IllegalStateException();
    }
}
