package ar.com.jss.controller;

import ar.com.jss.model.domain.Account;

import ar.com.jss.model.domain.AccountResource;
import ar.com.jss.service.data_access.AccountDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com.
 */
@RestController
@RequestMapping(Path.ACCOUNTS)
public class AccountController {
    private final AccountDataAccess accountDataAccess;

    @Autowired
    public AccountController(AccountDataAccess accountDataAccess) {
        this.accountDataAccess = accountDataAccess;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<AccountResource>> accounts(
            @PathVariable("user") long userId
    ) {
        Collection<AccountResource> resources = accountDataAccess.read(userId);
        return ResponseEntity.ok(resources);
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.GET)
    public ResponseEntity<AccountResource> account(
            @PathVariable("user") long userId,
            @PathVariable("account") long accountId
    ) {
        AccountResource resource = accountDataAccess.read(userId, accountId);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> newAccount(
            @PathVariable("user") long userId,
            @RequestBody AccountResource accountId) {
        AccountResource resource = accountDataAccess.create(userId, accountId);
        URI uri = URI.create(resource.getId().getHref());
        return ResponseEntity.created(uri).body(resource);
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.PUT)
    public ResponseEntity<AccountResource> updateAccount(
            @PathVariable("user") long userId,
            @PathVariable("account") long accountId,
            @RequestBody AccountResource account
    ) {
        AccountResource resource = accountDataAccess.update(accountId, account);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAccount(
            @PathVariable("user") long userId,
            @PathVariable("account") long accountId) {
        accountDataAccess.delete(accountId);
        return ResponseEntity.noContent().build();
    }
}
