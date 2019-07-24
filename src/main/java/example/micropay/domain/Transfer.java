package example.micropay.domain;

import java.math.BigDecimal;

public class Transfer {

    private final Account sourceAccount;
    private final Account destinationAccount;
    private final BigDecimal amount;
    private final TransferLockRegistry transferLockRegistry;

    Transfer(Account sourceAccount, Account destinationAccount, BigDecimal amount, TransferLockRegistry transferLockRegistry) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.transferLockRegistry = transferLockRegistry;
    }

    void execute() {
        transferLockRegistry.lockFor(sourceAccount, destinationAccount);
        try {
            sourceAccount.snapshotBalance();
            destinationAccount.snapshotBalance();

            try {
                sourceAccount.decreaseBalanceBy(amount);
                destinationAccount.increaseBalanceBy(amount);
            } catch (Exception ex) {
                sourceAccount.revertBalance();
                destinationAccount.revertBalance();
                throw new TransferFailureException(ex);
            }
        } finally {
            transferLockRegistry.unlockFor(sourceAccount, destinationAccount);
        }
    }
}
