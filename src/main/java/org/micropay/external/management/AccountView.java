package org.micropay.external.management;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.micropay.domain.account.Account;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountView {

    private final String uniqueId;
    private final BigDecimal balance;

    @JsonCreator
    public AccountView(
            @JsonProperty("uniqueId") String unigueId,
            @JsonProperty("balance") BigDecimal balance) {
        this.uniqueId = unigueId;
        this.balance = balance;
    }

    static AccountView from(Account account) {
        return new AccountView(account.getUniqueId(), account.getBalance());
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountView that = (AccountView) o;
        return Objects.equals(uniqueId, that.uniqueId) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, balance);
    }
}
