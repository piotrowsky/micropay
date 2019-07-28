package org.micropay.domain.account;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import static java.lang.String.format;

public class Account {

    private final String uniqueId = UUID.randomUUID().toString();
    private BigDecimal balance;

    Account(BigDecimal initialBalance) {
        this.balance = initialBalance;
    }

    public void decreaseBalanceBy(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException(
                    format("Insufficient funds for account: %s. Requested amount: ", this, amount));
        }
        this.balance = balance.subtract(amount);
    }

    public void increaseBalanceBy(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("uniqueId", uniqueId)
                .add("balance", balance)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(uniqueId, account.uniqueId) &&
                Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId, balance);
    }
}
