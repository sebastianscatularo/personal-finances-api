package ar.com.jss.service.data_access;

import ar.com.jss.model.domain.Account;
import ar.com.jss.model.repository.AccountRepository;
import ar.com.jss.model.repository.entity.AccountEntity;
import ar.com.jss.service.assambler.AccountAssembler;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static com.google.common.collect.FluentIterable.from;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Service
public class AccountDataAccessImpl implements AccountDataAccess {
    private final AtomicLong aLong;
    private final AccountAssembler assembler;
    private final AccountRepository repository;

    @Autowired
    public AccountDataAccessImpl(AccountRepository repository, AccountAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
        this.aLong = new AtomicLong(repository.count());
    }
    @Override
    public Collection<Resource<Account>> read() {
        Collection<Resource<Account>> resources = from(repository.findAll()).transform(new Function<AccountEntity, Resource<Account>>() {
            @Override
            public Resource<Account> apply(AccountEntity input) {
                return assembler.toResource(input.toAccount());
            }
        }).toList();
        return resources;
    }

    @Override
    public Resource<Account> read(long account) {
        AccountEntity accountEntity = repository.findOne(account);
        Resource<Account> resource = assembler.toResource(accountEntity.toAccount());
        return resource;
    }

    @Override
    public Resource<Account> create(long user, Account account) {
        account.setId(aLong.incrementAndGet());
        Resource<Account> resource = assembler.toResource(account);
        return resource;
    }

    @Override
    public Resource<Account> update(Account account) {
        AccountEntity accountEntity = repository.save(AccountEntity.from(account));
        Resource<Account> resource = assembler.toResource(accountEntity.toAccount());
        return resource;
    }

    @Override
    public void delete(long account) {
        repository.delete(account);
    }
}
