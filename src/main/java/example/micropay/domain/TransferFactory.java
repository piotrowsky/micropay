package example.micropay.domain;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;

@Singleton
public class TransferFactory {

    private final AccountRepository accountRepository;
    private final TransferLockRegistry transferLockRegistry;
    private final AmountValidator amountValidator;

    @Inject
    TransferFactory(AccountRepository accountRepository, TransferLockRegistry transferLockRegistry, AmountValidator amountValidator) {
        this.accountRepository = accountRepository;
        this.transferLockRegistry = transferLockRegistry;
        this.amountValidator = amountValidator;
    }

    Transfer transferFor(String sourceAccountUniqueId, String destinationAccountUniqueId, BigDecimal amount) {
        amountValidator.validate(amount);
        Account sourceAccount = accountRepository.getByUniqueId(sourceAccountUniqueId);
        Account destinationAccount = accountRepository.getByUniqueId(destinationAccountUniqueId);
        return new Transfer(sourceAccount, destinationAccount, amount, transferLockRegistry);
    }
}
