package ar.com.jss.model.domain;

import ar.com.jss.model.domain.deserializer.CurrencyDeserializer;
import ar.com.jss.model.domain.deserializer.MoneyDeserializer;
import ar.com.jss.model.domain.serializer.CurrencySerializer;
import ar.com.jss.model.domain.serializer.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author sebastianscatularo@gmail.com.
 */
public class AccountResource extends ResourceSupport {
    private String name;
    @JsonSerialize(using = CurrencySerializer.class)
    @JsonDeserialize(using = CurrencyDeserializer.class)
    private Currency currency;

    @JsonSerialize(using = MoneySerializer.class)
    @JsonDeserialize(using = MoneyDeserializer.class)
    private BigDecimal amount = BigDecimal.ZERO;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
