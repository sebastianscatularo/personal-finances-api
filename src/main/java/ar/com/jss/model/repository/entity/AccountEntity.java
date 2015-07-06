package ar.com.jss.model.repository.entity;

import ar.com.jss.model.domain.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Currency;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Entity
@Table(name = "ACCOUNTS")
public class AccountEntity {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CURRENCY")
    private Currency currency;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Collection<TransactionEntity> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Collection<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public static AccountEntity from(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setName(account.getName());
        entity.setCurrency(account.getCurrency());
        return entity;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setId(getId());
        account.setName(getName());
        account.setCurrency(getCurrency());
        return account;
    }
}
