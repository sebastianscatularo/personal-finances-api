package ar.com.jss.service.data_access;

import ar.com.jss.model.domain.UserResource;
import ar.com.jss.model.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Service
public class UserDataAccessImpl implements UserDataAccess {
    private final ResourceAssemblerSupport<UserEntity, UserResource> assembler;

    @Autowired
    public UserDataAccessImpl(ResourceAssemblerSupport<UserEntity, UserResource> assembler) {
        this.assembler = assembler;
    }

    @Override
    public Collection<UserResource> getUsers() {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return asList(assembler.toResource(userEntity));
    }

    @Override
    public UserResource getUser(long user) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == userEntity.getId()) {
            return assembler.toResource(userEntity);
        }
        throw new IllegalStateException();
    }
}
