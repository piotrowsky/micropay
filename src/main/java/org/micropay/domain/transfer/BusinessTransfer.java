package org.micropay.domain.transfer;

import org.micropay.domain.account.Account;
import org.micropay.domain.account.InsufficientBalanceException;

import java.math.BigDecimal;
import java.util.UUID;

class BusinessTransfer implements Transfer {

    private final String uniqueId = UUID.randomUUID().toString();
    private final Account sourceAccount;
    private final Account destinationAccount;
    private final BigDecimal amount;

    BusinessTransfer(Account sourceAccount, Account destinationAccount, BigDecimal amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        try {
            sourceAccount.decreaseBalanceBy(amount);
        } catch (InsufficientBalanceException ex) {
            throw new TransferFailureException(ex);
        }
        destinationAccount.increaseBalanceBy(amount);
    }

    @Override
    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public String getSourceAccountUniqueId() {
        return sourceAccount.getUniqueId();
    }

    @Override
    public String getDestinationAccountUniqueId() {
        return destinationAccount.getUniqueId();
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }
}
