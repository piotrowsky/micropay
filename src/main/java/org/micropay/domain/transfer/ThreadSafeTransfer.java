package org.micropay.domain.transfer;

import org.micropay.domain.account.Account;

import java.math.BigDecimal;

class ThreadSafeTransfer implements Transfer {

    private static final Object TIE_BREAKING_LOCK = new Object();

    private final Account sourceAccount;
    private final Account destinationAccount;
    private final Transfer businessTransfer;

    ThreadSafeTransfer(Account sourceAccount, Account destinationAccount, BigDecimal amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.businessTransfer = new BusinessTransfer(sourceAccount, destinationAccount, amount);
    }

    @Override
    public void execute() {
        String sourceUniqueId = sourceAccount.getUniqueId();
        String destinationUniqueId = destinationAccount.getUniqueId();
        int lockOrder = sourceUniqueId.compareTo(destinationUniqueId);

        if (lockOrder < 0) {
            synchronized (sourceAccount) {
                synchronized (destinationAccount) {
                    businessTransfer.execute();
                }
            }
        } else if (lockOrder > 0) {
            synchronized (destinationAccount) {
                synchronized (sourceAccount) {
                    businessTransfer.execute();
                }
            }
        } else {
            synchronized (TIE_BREAKING_LOCK) {
                synchronized (sourceAccount) {
                    synchronized (destinationAccount) {
                        businessTransfer.execute();
                    }
                }
            }
        }
    }

    @Override
    public String getUniqueId() {
        return businessTransfer.getUniqueId();
    }

    @Override
    public String getSourceAccountUniqueId() {
        return businessTransfer.getSourceAccountUniqueId();
    }

    @Override
    public String getDestinationAccountUniqueId() {
        return businessTransfer.getDestinationAccountUniqueId();
    }

    @Override
    public BigDecimal getAmount() {
        return businessTransfer.getAmount();
    }
}
