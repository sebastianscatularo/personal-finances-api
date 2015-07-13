package ar.com.jss.controller;

import ar.com.jss.model.domain.Account;

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
public class AccountController {
    private final AccountDataAccess accountDataAccess;

    @Autowired
    public AccountController(AccountDataAccess accountDataAccess) {
        this.accountDataAccess = accountDataAccess;
    }

    @RequestMapping(value = Path.ACCOUNTS, method = RequestMethod.GET)
    public ResponseEntity<Collection<Resource<Account>>> accounts(
            @PathVariable("user") long userId
    ) {
        Collection<Resource<Account>> resources = accountDataAccess.read(userId);
        return ResponseEntity.ok(resources);
    }

    @RequestMapping(value = Path.ACCOUNTS + "/{account}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Account>> account(
            @PathVariable("user") long userId,
            @PathVariable("account") long accountId
    ) {
        Resource<Account> resource = accountDataAccess.read(userId, accountId);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(value = Path.ACCOUNTS, method = RequestMethod.POST)
    public ResponseEntity<Resource<Account>> newAccount(
            @PathVariable("user") long userId,
            @RequestBody Account accountId) {
        Resource<Account> resource = accountDataAccess.create(userId, accountId);
        URI uri = URI.create(resource.getId().getHref());
        return ResponseEntity.created(uri).body(resource);
    }

    @RequestMapping(value = Path.ACCOUNTS + "/{account}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Account>> updateAccount(
            @PathVariable("user") long userId,
            @PathVariable("account") long accountId,
            @RequestBody Account account
    ) {
        account.setId(accountId);
        Resource<Account> resource = accountDataAccess.update(account);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(value = Path.ACCOUNTS + "/{account}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAccount(
            @PathVariable("user") long userId,
            @PathVariable("account") long accountId) {
        accountDataAccess.delete(accountId);
        return ResponseEntity.noContent().build();
    }
}
