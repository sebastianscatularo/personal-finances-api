package ar.com.jss.controller;

import ar.com.jss.model.repository.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author sebastianscatularo@gmail.com.
 */
final class Path {
    public static final String USERS = "/users";
    public static final String ACCOUNTS     = "/users/{user}/accounts";
    public static final String TRANSACTIONS = "/users/{user}/accounts/{account}/transactions";
    private Path() {
    }
}
