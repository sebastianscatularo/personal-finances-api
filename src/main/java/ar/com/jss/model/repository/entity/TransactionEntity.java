package ar.com.jss.model.repository.entity;

import ar.com.jss.model.domain.Account;
import ar.com.jss.model.domain.Transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author sebastianscatularo@gmail.com.
 */
@Entity
@Table(name = "TRANSACTIONS")
public class TransactionEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private AccountEntity account;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Transaction toTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAccountId(getAccount().getId());
        transaction.setId(getId());
        transaction.setAmount(getAmount());
        transaction.setDescription(getDescription());
        return transaction;
    }

    public static TransactionEntity from(Transaction transactionObject) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setDescription(transactionObject.getDescription());
        transactionEntity.setAmount(transactionObject.getAmount());
        return transactionEntity;
    }
}
