package ar.com.jss.controller;

import ar.com.jss.model.repository.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author sebastianscatularo@gmail.com.
 */
final class Path {
    public static final String USER = "/users";
    public static final String ACCOUNT     = "/users/{user}/accounts";
    public static final String TRANSACTION = "/users/{user}/accounts/{account}/transactions";
    private Path() {
    }
}
