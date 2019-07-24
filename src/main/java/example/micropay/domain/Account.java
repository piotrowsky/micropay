package example.micropay.domain;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private final String uniqueId = UUID.randomUUID().toString();
    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal balanceSnapshot;

    Account() {
    }

    void snapshotBalance() {
        balanceSnapshot = balance;
    }

    void revertBalance() {
        balance = balanceSnapshot;
    }

    void decreaseBalanceBy(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }
        this.balance = balance.subtract(amount);
    }

    void increaseBalanceBy(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("uniqueId", uniqueId)
                .add("balance", balance)
                .toString();
    }
}
