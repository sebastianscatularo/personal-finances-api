package ar.com.jss.controller;

import ar.com.jss.model.domain.Transaction;
import ar.com.jss.service.data_access.TransactionDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author sebastianscatularo@gmail.com.
 */
@RestController
@RequestMapping(Path.TRANSACTION)
public class TransactionController {
    private final TransactionDataAccess transactionDataAccess;

    @Autowired
    public TransactionController(TransactionDataAccess  transactionDataAccess) {
        this.transactionDataAccess = transactionDataAccess;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Resource<Transaction>>> transactions(@PathVariable("account") long account) {
        Collection<Resource<Transaction>> resources = transactionDataAccess.read(account);
        return ResponseEntity.ok(resources);
    }

    @RequestMapping(value = "/{transaction}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Transaction>> getTransaction(
            @PathVariable("account") long account,
            @PathVariable("transaction") long transaction
    ) {
        Resource<Transaction> resource = transactionDataAccess.read(account, transaction);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Resource<Transaction>> newTransaction(@PathVariable("account") long account) {
        Resource<Transaction> resource = transactionDataAccess.create(account, new Transaction());
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(value = "/{transaction}",method = RequestMethod.PUT)
    public ResponseEntity<Resource<Transaction>> updateTransaction(
            @PathVariable("account") long account,
            @PathVariable("transaction") long transaction,
            @RequestBody Transaction transactionObject
    ) {
        Resource<Transaction> resource = transactionDataAccess.update(account, transaction, transactionObject);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(value = "/{transaction}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTransaction(@PathVariable("transaction") long transaction) {
        transactionDataAccess.delete(transaction);
        return ResponseEntity.noContent().build();
    }
}
