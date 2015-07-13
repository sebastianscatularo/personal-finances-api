package ar.com.jss.service.data_access;

import ar.com.jss.model.domain.Account;
import ar.com.jss.model.domain.AccountResource;
import ar.com.jss.model.repository.AccountRepository;
import ar.com.jss.model.repository.entity.AccountEntity;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import static com.google.common.collect.FluentIterable.from;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Service
public class AccountDataAccessImpl implements AccountDataAccess {
    private final AtomicLong aLong;
    private final ResourceAssemblerSupport<AccountEntity, AccountResource> assembler;
    private final AccountRepository repository;

    @Autowired
    public AccountDataAccessImpl(AccountRepository repository, ResourceAssemblerSupport<AccountEntity, AccountResource>  assembler) {
        this.repository = repository;
        this.assembler = assembler;
        this.aLong = new AtomicLong(repository.count());
    }

    @Override
    public Collection<AccountResource> read(long user) {
        Collection<AccountResource> resources = from(repository.findAll()).transform(new Function<AccountEntity, AccountResource>() {
            @Override
            public AccountResource apply(AccountEntity input) {
                return assembler.toResource(input);
            }
        }).toList();
        return resources;
    }

    @Override
    public AccountResource read(long user, long account) {
        AccountEntity accountEntity = repository.findOne(account);
        AccountResource resource = assembler.toResource(accountEntity);
        return resource;
    }

    @Override
    public AccountResource create(long user, AccountResource account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(aLong.incrementAndGet());
        AccountResource resource = assembler.toResource(accountEntity);
        return resource;
    }

    @Override
    public AccountResource update(long accountId, AccountResource account) {
        if (aLong.get() < accountId) {
            throw new IllegalStateException();
        }
        AccountEntity accountEntity = repository.save(AccountEntity.from(accountId, account));
        AccountResource resource = assembler.toResource(accountEntity);
        return resource;
    }

    @Override
    public void delete(long account) {
        repository.delete(account);
    }
}
