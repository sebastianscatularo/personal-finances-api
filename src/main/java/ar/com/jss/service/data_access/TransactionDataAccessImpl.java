package ar.com.jss.service.data_access;

import ar.com.jss.model.domain.Transaction;
import ar.com.jss.model.repository.AccountRepository;
import ar.com.jss.model.repository.TransactionRepository;
import ar.com.jss.model.repository.entity.AccountEntity;
import ar.com.jss.model.repository.entity.TransactionEntity;
import ar.com.jss.service.assambler.TransactionAssembler;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.google.common.collect.FluentIterable.from;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Service
public class TransactionDataAccessImpl implements TransactionDataAccess {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionAssembler assembler;

    @Autowired
    public TransactionDataAccessImpl(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository,
            TransactionAssembler assembler) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.assembler = assembler;
    }
    @Override
    public Collection<Resource<Transaction>> read(long account) {
        AccountEntity accountEntity = accountRepository.findOne(account);
        Collection<Resource<Transaction>> resources = from(accountEntity.getTransactions()).transform(new Function<TransactionEntity, Resource<Transaction>>() {
            @Override
            public Resource<Transaction> apply(TransactionEntity input) {
                return assembler.toResource(input.toTransaction());
            }
        }).toList();
        return resources;
    }

    @Override
    public Resource<Transaction> read(long account, long transaction) {
        TransactionEntity transactionEntity = transactionRepository.findOne(transaction);
        return assembler.toResource(transactionEntity.toTransaction());
    }

    @Override
    public Resource<Transaction> create(long account, Transaction transaction) {
        transaction.setAccountId(account);
        transaction.setId(transactionRepository.count() + 1);
        return assembler.toResource(transaction);
    }

    @Override
    public Resource<Transaction> update(long account, long transaction, Transaction transactionObject) {
        AccountEntity accountEntity = accountRepository.findOne(account);
        TransactionEntity transactionEntity = TransactionEntity.from(transactionObject);
        transactionEntity.setId(transaction);
        transactionEntity.setAccount(accountEntity);
        transactionEntity = transactionRepository.save(transactionEntity);
        return assembler.toResource(transactionEntity.toTransaction());
    }

    @Override
    public void delete(long transaction) {
        transactionRepository.delete(transaction);
    }
}
