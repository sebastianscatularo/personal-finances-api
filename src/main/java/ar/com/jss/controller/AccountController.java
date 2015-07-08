package ar.com.jss.controller;

import ar.com.jss.model.domain.Account;

import ar.com.jss.service.data_access.AccountDataAccess;
import ar.com.jss.service.data_access.AccountDataAccessImpl;
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
@RequestMapping(Path.ACCOUNT)
public class AccountController {
    private final AccountDataAccess accountDataAccess;

    @Autowired
    public AccountController(AccountDataAccess accountDataAccess) {
        this.accountDataAccess = accountDataAccess;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Resource<Account>>> accounts(@PathVariable("account") long user) {
        Collection<Resource<Account>> resources = accountDataAccess.read();
        return ResponseEntity.ok(resources);
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Account>> account(@PathVariable("account") long account) {
        Resource<Account> resource = accountDataAccess.read(account);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Account>> newAccount(@RequestBody Account account) {
        Resource<Account> resource = accountDataAccess.create(account);
        URI uri = URI.create(resource.getId().getHref());
        return ResponseEntity.created(uri).body(resource);
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Account>> updateAccount(@PathVariable("account") Long accountId,@RequestBody Account account) {
        account.setId(accountId);
        Resource<Account> resource = accountDataAccess.update(account);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAccount(@PathVariable("account") long account) {
        accountDataAccess.delete(account);
        return ResponseEntity.noContent().build();
    }
}
