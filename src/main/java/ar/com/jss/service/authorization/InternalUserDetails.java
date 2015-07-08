package ar.com.jss.service.authorization;

import ar.com.jss.model.repository.entity.RoleEntity;
import ar.com.jss.model.repository.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com.
 */
final class InternalUserDetails implements UserDetails {
    private final UserEntity userEntity;

    public InternalUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<RoleEntity> getAuthorities() {
        return userEntity.getRoles();
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
