package org.micropay.domain.transfer;

import org.micropay.domain.account.AccountRepository;
import org.micropay.domain.account.Account;
import org.micropay.domain.transfer.validation.TransferCorrectnessValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;

@Singleton
public class TransferFactory {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final TransferCorrectnessValidator transferCorrectnessValidator;

    @Inject
    TransferFactory(
            AccountRepository accountRepository,
            TransferRepository transferRepository,
            TransferCorrectnessValidator transferCorrectnessValidator) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
        this.transferCorrectnessValidator = transferCorrectnessValidator;
    }

    public Transfer transferFor(String sourceAccountUniqueId, String destinationAccountUniqueId, BigDecimal amount) {
        transferCorrectnessValidator.validate(sourceAccountUniqueId, destinationAccountUniqueId, amount);

        Account sourceAccount = accountRepository.getByUniqueId(sourceAccountUniqueId);
        Account destinationAccount = accountRepository.getByUniqueId(destinationAccountUniqueId);

        ThreadSafeTransfer threadSafeTransfer = new ThreadSafeTransfer(sourceAccount, destinationAccount, amount);
        return new PersistableTransfer(transferRepository, threadSafeTransfer);
    }
}
